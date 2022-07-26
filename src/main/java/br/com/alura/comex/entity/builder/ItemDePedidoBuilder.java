package br.com.alura.comex.entity.builder;

import br.com.alura.comex.entity.ItemDePedido;
import br.com.alura.comex.entity.Pedido;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.entity.TipoDesconto;

import java.math.BigDecimal;

public class ItemDePedidoBuilder {

    private BigDecimal precoUnitario;

    private Integer quantidade;

    private Pedido pedido;

    private Produto produto;

    private BigDecimal desconto;

    private TipoDesconto tipoDesconto;

    public ItemDePedidoBuilder precoUnitario(BigDecimal preco) {
        this.precoUnitario = preco;
        return this;
    }

    public ItemDePedidoBuilder quantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ItemDePedidoBuilder pedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public ItemDePedidoBuilder produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public ItemDePedidoBuilder desconto(BigDecimal desconto) {
        this.desconto = desconto;
        return this;
    }

    public ItemDePedidoBuilder tipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
        return this;
    }

    public ItemDePedido build() {
        return new ItemDePedido(quantidade, pedido, produto, desconto, tipoDesconto);
    }


}
