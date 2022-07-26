package br.com.alura.comex;

import br.com.alura.comex.dao.*;
import br.com.alura.comex.entity.*;
import br.com.alura.comex.relatorio.RelatorioDeQtdPedidosCliente;
import br.com.alura.comex.relatorio.RelatorioDeQtdProdutosVendidosCategoria;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Categoria informatica = new Categoria("INFORMATICA", StatusCategoria.ATIVA);

        Endereco endereco = new Endereco("Av. quinta", "424", "", "Santa Genebra", "Campinas", "SP");

        Cliente cliente = new Cliente("Bela", 413566673877L, 38245566L, endereco);

        Produto impressora = new Produto("Mouse", "", new BigDecimal(2), 2, informatica);

        TipoDesconto fidelidade = new TipoDesconto("FIDELIDADE");



        List<TipoDesconto> listaDesconto = new ArrayList<>();
        listaDesconto.add(fidelidade);

        Pedido novoPedido = new Pedido(cliente, BigDecimal.ZERO, listaDesconto);
        ItemDePedido item = new ItemDePedido(2, novoPedido, impressora, BigDecimal.ZERO, fidelidade);

        Pedido pedido2 = new Pedido(cliente, BigDecimal.TEN, listaDesconto);

        novoPedido.adicionarItem(item);


        pedido2.adicionarItem(item);

        EntityManager em = JPAUtil.getEntityManager();

        ClienteDAO clienteDAO = new ClienteDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        TipoDescontoDAO tipoDescontoDAO = new TipoDescontoDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);


        em.getTransaction().begin();

        clienteDAO.cadastrar(cliente);
        categoriaDAO.cadastrar(informatica);
        produtoDAO.cadastrar(impressora);
        tipoDescontoDAO.cadastrar(fidelidade);
        pedidoDAO.cadastrar(novoPedido);
        pedidoDAO.cadastrar(pedido2);

        List<RelatorioDeQtdPedidosCliente> relatorio = pedidoDAO.relatorioQuantidadePedidosCliente();
        List<RelatorioDeQtdProdutosVendidosCategoria> relatorioCategoria = pedidoDAO.relatorioQtdProdutosVendidosCategoria();
        List<Produto> listaProduto = pedidoDAO.relatorioProdutosMaisVendidos();
        em.getTransaction().commit();
        em.close();


        relatorio.stream().forEach(System.out::println);
        relatorioCategoria.stream().forEach(System.out::println);
        listaProduto.stream().forEach(System.out::println);

    }
}
