package br.com.alura.comex.controller;

import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaDAO;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import br.com.alura.comex.compartilhado.infra.produto.DimensaoEntity;
import br.com.alura.comex.estoque.infra.produto.ProdutoEstoqueEntity;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoriaDAO categoriaRepository;

    private CategoriaEntity INFORMATICA;

    @BeforeEach
    void setup() {
        INFORMATICA = CategoriaEntity.builder()
                .nome("INFORMATICA")
                .status(StatusCategoria.ATIVA)
                .produtoEntities(new ArrayList<>())
                .build();
        entityManager.persist(INFORMATICA);

    }


    @Test
    public void deveriaListar3Categorias() throws Exception {
        URI uri = new URI("/api/categorias");

        CategoriaEntity FILMES = CategoriaEntity.builder()
                .nome("FILMES")
                .status(StatusCategoria.ATIVA)
                .produtoEntities(new ArrayList<>())
                .build();

        CategoriaEntity LIVROS = CategoriaEntity.builder()
                .nome("LIVROS")
                .status(StatusCategoria.ATIVA)
                .produtoEntities(new ArrayList<>())
                .build();

        entityManager.persist(FILMES);
        entityManager.persist(LIVROS);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].id", equalTo(INFORMATICA.getId().intValue())))
                .andExpect(jsonPath("$.[0].nome", equalTo(INFORMATICA.getNome())))
                .andExpect(jsonPath("$.[0].status", equalTo(INFORMATICA.getStatus().toString())))
                .andExpect(jsonPath("$.[1].id", equalTo(FILMES.getId().intValue())))
                .andExpect(jsonPath("$.[1].nome", equalTo(FILMES.getNome())))
                .andExpect(jsonPath("$.[1].status", equalTo(FILMES.getStatus().toString())))
                .andExpect(jsonPath("$.[2].id", equalTo(LIVROS.getId().intValue())))
                .andExpect(jsonPath("$.[2].nome", equalTo(LIVROS.getNome())))
                .andExpect(jsonPath("$.[2].status", equalTo(LIVROS.getStatus().toString())));
    }

    @Test
    void deveriaRetornarDetalhesCategoriaCom2Produtos() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        ProdutoEstoqueEntity teclado = ProdutoEstoqueEntity.builder()
                .nome("teclado")
                .descricao("Dell")
                .precoUnitario(new BigDecimal("25.4"))
                .quantidadeEstoque(3)
                .categoria(INFORMATICA)
                .dimensao(DimensaoEntity.builder()
                        .comprimento(123)
                        .altura(500)
                        .largura(5555)
                        .peso(new BigDecimal(4))
                        .build())
                .build();

        ProdutoEstoqueEntity tela = ProdutoEstoqueEntity.builder()
                .nome("tela")
                .descricao("Dell")
                .precoUnitario(new BigDecimal("45.00"))
                .quantidadeEstoque(10)
                .categoria(INFORMATICA)
                .dimensao(DimensaoEntity.builder()
                        .comprimento(123)
                        .altura(500)
                        .largura(5555)
                        .peso(new BigDecimal(4))
                        .build())
                .build();

        entityManager.persist(teclado);
        entityManager.persist(tela);

        INFORMATICA.setProdutoEntities(List.of(teclado, tela));

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isOk())
                .andDo(log())
                .andExpect(jsonPath("$.nome", equalTo(INFORMATICA.getNome())))
                .andExpect(jsonPath("$.status", equalTo(INFORMATICA.getStatus().toString())))
                .andExpect(jsonPath("$.produtos", equalTo(INFORMATICA.getProdutoEntities().stream().map(ProdutoEstoqueEntity::getNome).collect(Collectors.toList()))));
    }

    @Test
    void deveriaRetornarDetalhesCategoriaSemProdutos() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isOk())
                .andDo(log())
                .andExpect(jsonPath("$.nome", equalTo(INFORMATICA.getNome())))
                .andExpect(jsonPath("$.status", equalTo(INFORMATICA.getStatus().toString())))
                .andExpect(jsonPath("$.produtos", equalTo(new ArrayList<>(INFORMATICA.getProdutoEntities()))));
    }


    @Test
    public void deveriaCadastrarCategoria() throws Exception {
        URI uri = new URI("/api/categorias");

        JSONObject json = new JSONObject().put("nome", "LIVROS");
        String request = json.toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(201));

    }

    @Test
    public void deveriaRetornar404ParaCategoriaNaoEncontradoParaAlteração() throws Exception {

        URI uri = new URI("/api/categorias/" + 99);

        String request = new JSONObject()
                .put("nome", "FILMES").toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaAlterarStatusCategoria() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(200));

    }

    @Test
    public void deveriaRetornar404NaAlteracaoStatusCategoria() throws Exception {

        URI uri = new URI("/api/categorias/298383");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(404));

    }

    @Test
    public void deveriaRetornar400ParaAlteracaoComBodyVazio() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(400));

    }

    @Test
    public void deveriaDeletarCategoria() throws Exception {

        URI uri = new URI("/api/categorias/" + INFORMATICA.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}