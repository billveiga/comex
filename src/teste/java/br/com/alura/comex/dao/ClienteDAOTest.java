package br.com.alura.comex.dao;


import br.com.alura.comex.entity.Cliente;
import br.com.alura.comex.entity.Endereco;
import br.com.alura.comex.entity.builder.ClienteBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class ClienteDAOTest {

    @Mock
    private ClienteDAO clienteDAO;

    @Test
    public void testeCadastrar() {

        clienteDAO = Mockito.mock(ClienteDAO.class);

        Endereco endereco = new Endereco("Rua Taylor", "39", "611", "Glória", "Rio de Janeiro", "RJ");

        Cliente maria = new ClienteBuilder()
                .nome("Bill")
                .cpf(12345678900)
                .endereco(endereco)
                .telefone(21999999999)
                .listaDePedido(new ArrayList<>())
                .build();

        clienteDAO.cadastrar(Bill);
        Mockito.verify(clienteDAO, Mockito.times(1)).cadastrar(Mockito.any(Cliente.class));

    }

    @Test
    public void deveriaRetornarUmCliente() {
        clienteDAO = Mockito.mock(ClienteDAO.class);

        Endereco endereco = new Endereco("Rua Taylor", "39", "611", "Glória", "Rio de Janeiro", "RJ");

        Cliente maria = new ClienteBuilder()
                .id()
                .nome("Julia")
                .cpf(12345678901)
                .endereco(endereco)
                .telefone(2199999998)
                .listaDePedido(new ArrayList<>())
                .build();

        Mockito.when(clienteDAO.buscaPorId(Mockito.any())).thenReturn(julia);

        Cliente retornoJulia = clienteDAO.buscaPorId(julia.getId());

        Assertions.assertEquals(julia, retornoJulia);

    }

    @Test
    public void deveriaListarTodosClientes() {
        clienteDAO = Mockito.mock(ClienteDAO.class);

        List<Cliente> clientesEsperados = clientesTeste();

        Mockito.when(clienteDAO.listaTodos()).thenReturn(clientesEsperados);

        List<Cliente> clientesRecuperados = clienteDAO.listaTodos();

        Assertions.assertEquals(clientesEsperados, clientesRecuperados);

    }

    private List<Cliente> clientesTeste() {
        List<Cliente> lista = new ArrayList<>();

        Endereco endereco = new Endereco("Rua Taylor", "39", "611", "Glória", "Rio de Janeiro", "RJ");

        lista.add(new ClienteBuilder()
                .id()
                .nome("Julia")
                .cpf(12345678901)
                .endereco(endereco)
                .telefone(2199999998)
                .listaDePedido(new ArrayList<>())
                .build());

        lista.add(new ClienteBuilder()
                .id()
                .nome("Rod")
                .cpf(12345678902)
                .endereco(endereco)
                .telefone(2199999997)
                .listaDePedido(new ArrayList<>())
                .build());

        lista.add(new ClienteBuilder()
                .id()
                .nome("Sara")
                .cpf(12345678903)
                .endereco(endereco)
                .telefone(2199999996)
                .listaDePedido(new ArrayList<>())
                .build());

        return lista;
    }


}
