package br.com.alura.comex.controller;


import br.com.alura.comex.compartilhado.infra.cliente.ClienteEntity;
import br.com.alura.comex.compartilhado.infra.cliente.ClienteRepositoryImpl;
import br.com.alura.comex.compartilhado.infra.cliente.EnderecoEntity;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestEntityManager
@ActiveProfiles("test")
class ClienteEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepositoryImpl clienteDAO;

    private EnderecoEntity DEFAULT_ENDERECO = new EnderecoEntity("Rua Taylor", "39", "611", "Gloria", "Rio de Janeiro", "RJ");


    @Test
    public void deveriaRetornarTodosClientesOrdenadosPorNomeUtilizandoPaginacaoPadrao() throws Exception {

        ClienteEntity Julia = ClienteEntity.builder()
                .nome("Julia")
                .cpf("12345678900")
                .endereco(DEFAULT_ENDERECO)
                .ddd("21")
                .numeroTelefone("99999999")
                .pedidos(new ArrayList<>())
                .build();

        ClienteEntity sara = ClienteEntity.builder()
                .nome("Sara")
                .cpf("12345678901")
                .endereco(DEFAULT_ENDERECO)
                .ddd("21")
                .numeroTelefone("99999998")
                .pedidos(new ArrayList<>())
                .build();


        clienteDAO.cadastrarCliente(kelvin.paraCliente());
        clienteDAO.cadastrarCliente(amanda.paraCliente());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].nome").value("Sara"))
                .andExpect(jsonPath("$.content[1].nome").value("Julia"));

    }

    @Test
    public void deveriaPersistirUmCliente() throws Exception {

        String request = new JSONObject()
                .put("nome", "Julia")
                .put("cpf", "12345678900")
                .put("telefone", "99999999")
                .put("rua", "Taylor")
                .put("numero", "39")
                .put("complemento", "Ap.611")
                .put("bairro", "Gloria")
                .put("cidade", "Rio de Janeiro")
                .put("estado", "RJ").toString();


        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Julia"));

    }

    @Test
    public void deveriaGerarErroDeValidaçãoDeCampos() throws Exception {

        String request = new JSONObject()
                .put("nome", "Julia")
                .put("cpf", "12345678900")
                .put("rua", "Taylor")
                .put("numero", "39")
                .put("complemento", "ap.611")
                .put("bairro", "Gloria")
                .put("cidade", "Rio de Janeiro")
                .put("estado", "RJ").toString();


        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


    }


}