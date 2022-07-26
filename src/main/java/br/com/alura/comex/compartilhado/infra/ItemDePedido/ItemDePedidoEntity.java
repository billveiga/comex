package br.com.alura.comex.compartilhado.infra.ItemDePedido;

import br.com.alura.comex.compartilhado.entity.itemDePedido.ItemDePedido;
import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import br.com.alura.comex.compartilhado.entity.enuns.TipoDescontoItem;
import br.com.alura.comex.compartilhado.infra.pedido.PedidoEntity;
import br.com.alura.comex.estoque.infra.produto.ProdutoEstoqueEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDePedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PedidoEntity pedidoEntity;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProdutoEstoqueEntity produtoEstoqueEntity;

    @Column(nullable = false)
    private BigDecimal desconto = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private TipoDescontoItem tipoDesconto = TipoDescontoItem.NENHUM;

    public ItemDePedidoEntity(Integer quantidade, ProdutoEstoqueEntity produtoEstoqueEntity) {
        this.quantidade = quantidade;
        this.produtoEstoqueEntity = produtoEstoqueEntity;
        this.precoUnitario = produtoEstoqueEntity.getPrecoUnitario();
    }

    public static ItemDePedidoEntity converter(ItemDePedido item){
        return ItemDePedidoEntity.builder()
                .id(item.getId())
                .precoUnitario(item.getPrecoUnitario())
                .quantidade(item.getQuantidade())
                .produtoEstoqueEntity(ProdutoEstoqueEntity.converter(item.getProdutoEstoque()))
                .desconto(item.getDesconto())
                .tipoDesconto(item.getTipoDesconto())
                .pedidoEntity(new PedidoEntity())
                .build();
    }

    public ItemDePedido paraItemDePedido(){
        return ItemDePedido.builder()
                .id(this.id)
                .precoUnitario(this.precoUnitario)
                .quantidade(this.quantidade)
                .produtoEstoque(this.produtoEstoqueEntity.paraProduto())
                .desconto(this.desconto)
                .tipoDesconto(this.tipoDesconto)
                .pedido(new Pedido())
                .build();
    }

    public BigDecimal getValorTotalItem() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }
}
