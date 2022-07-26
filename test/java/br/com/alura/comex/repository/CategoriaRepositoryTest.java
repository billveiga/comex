package br.com.alura.comex.repository;

import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaDAO;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaDAO repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaRetornarListaDeCategorias(){

        CategoriaEntity categoria = CategoriaEntity.builder()
                .id(1L)
                .nome("GAMES")
                .status(StatusCategoria.ATIVA)
                .produtoEntities(new ArrayList<>())
                .build();


        CategoriaEntity categoriaSalva = repository.save(categoria);

        Optional<CategoriaEntity> retorno = repository.findById(categoria.getId());

        assertThat(categoriaSalva).isEqualTo(retorno.get());

    }

}