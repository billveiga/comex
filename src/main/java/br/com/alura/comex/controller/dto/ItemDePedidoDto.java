package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.ItemDePedido;

import java.math.BigDecimal;

public class ItemDePedidoDto {
    private Long id;
    
    private BigDecimal precoUnitario;
    
    private Integer quantidade;
    
    private String produto;

    private String categoria;

    private BigDecimal desconto;

    private BigDecimal valorFinal;

    public ItemDePedidoDto(ItemDePedido item) {
        this.id = item.getId();
        this.precoUnitario = item.getPrecoUnitario();
        this.quantidade = item.getQuantidade();
        this.produto = item.getProdutoId().getName();
        this.categoria = item.getProdutoId().getCategoria().getNome();
        this.desconto = item.getDesconto();
        this.valorFinal = item.getValorTotalItem();
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

    public String getProduto() {
        return produto;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    @Override
    public String toString() {
        return "ItemDePedidoDto{" +
                "id=" + id +
                ", precoUnitario=" + precoUnitario +
                ", quantidade=" + quantidade +
                ", produto='" + produto + '\'' +
                ", categoria='" + categoria + '\'' +
                ", desconto=" + desconto +
                ", valorFinal=" + valorFinal +
                '}';
    }
}
