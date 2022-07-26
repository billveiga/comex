package br.com.alura.comex.compartilhado.infra.pedido;

import br.com.alura.comex.compartilhado.entity.categoria.RelatorioPedidosPorCategoriaProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDAO extends JpaRepository<PedidoEntity, Long> {

    @Query(value = "SELECT c.nome, SUM(item.quantidade) AS quantidadeProdutos, SUM((item.preco_unitario * item.quantidade)) AS montanteVendido " +
            "FROM itens_pedido item " +
            "JOIN produtos p " +
            "JOIN categorias c " +
            "WHERE item.produto_id = p.id AND p.categoria_id = c.id " +
            "GROUP BY item.produto_id, c.nome", nativeQuery = true)
    List<RelatorioPedidosPorCategoriaProjecao> findPedidosPorCategoria();

}
