package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Produto;

import java.math.BigDecimal;

public class DetalhesDeProdutoDto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private String categoria;

    public DetalhesDeProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getName();
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecoUnitario();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.categoria = produto.getCategoria().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String getCategoria() {
        return categoria;
    }
}
