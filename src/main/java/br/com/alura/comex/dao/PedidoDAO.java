package br.com.alura.comex.dao;

import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.relatorio.RelatorioDeQtdPedidosCliente;
import br.com.alura.comex.entity.Pedido;
import br.com.alura.comex.relatorio.RelatorioDeQtdProdutosVendidosCategoria;
import br.com.alura.comex.relatorio.RelatorioProdutosVendidos;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }

    public void atualiza(Pedido pedido) {
        this.em.merge(pedido);

    }
    public List<RelatorioDeQtdPedidosCliente> relatorioQuantidadePedidosCliente(){
        String query =
        "SELECT new br.com.alura.comex.relatorio.RelatorioDeQtdPedidosCliente("
                + "cliente.nome, "
                + "SUM(pedido.cliente)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.cliente cliente "
                + "GROUP BY pedido.cliente ";

        return em.createQuery(query, RelatorioDeQtdPedidosCliente.class).getResultList();
    }

    public List<RelatorioDeQtdProdutosVendidosCategoria> relatorioQtdProdutosVendidosCategoria(){
        String query = "SELECT new br.com.alura.comex.relatorio.RelatorioDeQtdProdutosVendidosCategoria("
                + "categoria.nome, "
                + "SUM(item.quantidade)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "JOIN produto.categoria categoria "
                + "GROUP BY produto.categoria ";

        return em.createQuery(query, RelatorioDeQtdProdutosVendidosCategoria.class).getResultList();
    }

    public List<Produto> relatorioProdutosMaisVendidos(){

        String query = "SELECT item.produto "
                        + "FROM Pedido p "
                        + "JOIN p.itens item "
                        + "GROUP BY item.produto "
                        + "HAVING SUM(item.produto) > 3";

        return em.createQuery(query, Produto.class).getResultList();
    }



}
