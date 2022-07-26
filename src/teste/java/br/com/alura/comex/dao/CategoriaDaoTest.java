package br.com.alura.comex.dao;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.StatusCategoria;
import br.com.alura.comex.entity.builder.CategoriaBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDaoTest {

    @InjectMocks
    private CategoriaDAO categoriaDAO;


    @Test
    public void testeRetornarUmaCategoria() {
        categoriaDAO = Mockito.mock(CategoriaDAO.class);

        Categoria categoriaEsperada = new CategoriaBuilder().id().nome("AUTO").produtos(new ArrayList<>()).status(StatusCategoria.ATIVA).build();

        Mockito.when(categoriaDAO.buscarPorId(Mockito.any())).thenReturn(categoriaEsperada);

        Assertions.assertEquals(categoriaEsperada, categoriaDAO.buscarPorId(Mockito.any()));
    }

    @Test
    public void deveriaListarTodasCategorias() {
        categoriaDAO = Mockito.mock(CategoriaDAO.class);

        List<Categoria> categoriasEsperada = gerarCategorias();

        Mockito.when(categoriaDAO.listaTodas()).thenReturn(categoriasEsperada);

        Assertions.assertEquals(categoriasEsperada, categoriaDAO.listaTodas());
    }


    @Test
    public void deveriaListarTodasCategoriasIndisponiveis() {
        categoriaDAO = Mockito.mock(CategoriaDAO.class);

        List<Categoria> categoriasEsperada = gerarCategorias();

        Mockito.when(categoriaDAO.listaInativas()).thenReturn(categoriasEsperada);

        Assertions.assertEquals(categoriasEsperada, categoriaDAO.listaInativas());

    }

    private List<Categoria> gerarCategorias() {
        List<Categoria> lista = new ArrayList<>();

        lista.add(new CategoriaBuilder().id().nome("AUTO").produtos(new ArrayList<>()).status(StatusCategoria.ATIVA).build());
        lista.add(new CategoriaBuilder().id().nome("AUTO").produtos(new ArrayList<>()).status(StatusCategoria.ATIVA).build());
        lista.add(new CategoriaBuilder().id().nome("TECNOLOGIA").produtos(new ArrayList<>()).status(StatusCategoria.ATIVA).build());
        lista.add(new CategoriaBuilder().id().nome("LIVROS").produtos(new ArrayList<>()).status(StatusCategoria.INATIVA).build());


        return lista;
    }
}
