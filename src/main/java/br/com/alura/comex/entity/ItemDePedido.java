package br.com.alura.comex.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedido pedido;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produto produto;

    private BigDecimal desconto;

    @OneToOne(fetch = FetchType.LAZY)
    private TipoDesconto tipoDesconto;

    public ItemDePedido(){
        super();
    }

    public ItemDePedido(Integer quantidade, Pedido pedido, Produto produto, BigDecimal desconto, TipoDesconto tipoDesconto) {;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
        this.precoUnitario = produto.getPrecoUnitario();
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getValorTotalItem(){
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Pedido getPedidoId() {
        return pedido;
    }

    public Produto getProdutoId() {
        return produto;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }

    public void setPedido(Pedido pedido) {
    }
}
