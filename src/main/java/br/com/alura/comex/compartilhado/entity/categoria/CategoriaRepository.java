package br.com.alura.comex.compartilhado.entity.categoria;

import java.util.List;

public interface CategoriaRepository {

    List<Categoria> listarTodasCategorias();

    Categoria cadastrarCategoria(Categoria categoria);

    void deletarCategoria(Long id);

    Categoria buscarCategoria(Long id);

    Categoria atualizar(Long id, String nomeCategoria);

    Categoria atualizarStatus(Long id);

}
