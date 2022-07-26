package br.com.alura.comex.controller;


import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;
import org.json.JSONException;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProdutoRepository produtoRepository;

    private Categoria INFORMATICA;

    private Produto IMPRESSORA;

    @BeforeEach
    void setup() {
        INFORMATICA = new Categoria("INFORMATICA");

        IMPRESSORA = new Produto("impressora", "HP720", new BigDecimal("300"), 10, INFORMATICA );

        entityManager.persist(INFORMATICA);

        entityManager.persist(IMPRESSORA);
    }


    @Test
    public void deveriaListar2ProdutosOrdenadosPorNome() throws Exception {
        URI uri = new URI("/api/produtos");

        Produto teclado = new Produto("teclado", "Dell", new BigDecimal("45.90"), 10, INFORMATICA );
        entityManager.persist(teclado);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].nome", is("impressora")))
                .andExpect(jsonPath("$.content[1].nome", is("teclado")));

    }
    @Test
    void deveriaRetornarInformacoesMouse() throws Exception {
        URI uri = new URI("/api/produtos/" + IMPRESSORA.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isOk())
                .andDo(log())
                .andExpect(jsonPath("$.id", equalTo(IMPRESSORA.getId().intValue())))
                .andExpect(jsonPath("$.nome", equalTo(IMPRESSORA.getName())))
                .andExpect(jsonPath("$.descricao", equalTo(IMPRESSORA.getDescricao())))
                .andExpect(jsonPath("$.quantidadeEstoque", equalTo(IMPRESSORA.getQuantidadeEstoque())))
                .andExpect(jsonPath("$.categoria", equalTo(IMPRESSORA.getCategoria().getNome())));
    }



    @Test
    public void deveriaCadastrarProduto() throws Exception {
        URI uri = new URI("/api/produtos");

        JSONObject json = criarObjetoJson();
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
    public void deveriaAlterarQuantidadeEmEstoqueDoProduto() throws Exception {

        URI uri = new URI("/api/produtos/" + IMPRESSORA.getId() );

        String request = criarObjetoProdutoJsonAlterado(IMPRESSORA).toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(200));

    }

    @Test
    public void deveriaRetornar404ParaProdutoNaoEncontradoParaAlteração() throws Exception {

        URI uri = new URI("/api/produtos/1010");

        String request = criarObjetoProdutoJsonAlterado(IMPRESSORA).toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(404));

    }

    @Test
    public void deveriaRetornar400ParaAlteracaoComBodyVazio() throws Exception {

        URI uri = new URI("/api/produtos/" + IMPRESSORA.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(400));

    }

    @Test
    public void deveriaDeletarProduto() throws Exception {

        URI uri = new URI("/api/produtos/" + IMPRESSORA.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete( uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private JSONObject criarObjetoProdutoJsonAlterado(Produto produto) throws JSONException {

        return new JSONObject()
                .put("nome", produto.getName())
                .put("descricao", produto.getDescricao())
                .put("precoUnitario",produto.getPrecoUnitario())
                .put("quantidadeEstoque", 10)
                .put("categoria", GAMES.getId());
    }

    private JSONObject criarObjetoJson() throws JSONException {

        return new JSONObject()
                .put("nome","Playstation5")
                .put("descricao", "modelo B")
                .put("precoUnitario",4000.00)
                .put("quantidadeEstoque", 10)
                .put("categoria", GAMES.getId());
    }


}