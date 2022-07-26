package br.com.alura.comex.main;

import br.com.alura.comex.dao.ClienteDAO;
import br.com.alura.comex.dao.PedidoDAO;
import br.com.alura.comex.entity.Cliente;
import br.com.alura.comex.entity.Endereco;
import br.com.alura.comex.entity.builder.ClienteBuilder;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class MainClienteDAO {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        ClienteDAO clienteDAO = new ClienteDAO(em);

        Endereco endereco = new Endereco("Rua Taylor", "39", "611", "Glória", "Rio de Janeiro", "RJ");

        Cliente cliente = new ClienteBuilder()
                .id()
                .nome("Julia")
                .cpf(12345678901L)
                .endereco(endereco)
                .telefone(2199999998L)
                .build();

        Cliente arthur = new ClienteBuilder()
                .id()
                .nome("Rod")
                .cpf(12345678902L)
                .endereco(endereco)
                .telefone(2199999997L)
                .build();

        Cliente joao = new ClienteBuilder()
                .id()
                .nome("Sara")
                .cpf(12345678903L)
                .endereco(endereco)
                .telefone(2199999996L)
                .build();

        PedidoDAO pedido = new PedidoDAO(em);

        em.getTransaction().begin();

        clienteDAO.cadastrar(cliente);
        clienteDAO.cadastrar(arthur);
        clienteDAO.cadastrar(joao);

        List<Cliente> listaJulia = clienteDAO.buscaPorNome("Julia");
        em.getTransaction().commit();
        em.close();

        listaJulia.stream().forEach(a -> System.out.println("BUSCA POR NOME: " + a.getNome()));


    }


}
