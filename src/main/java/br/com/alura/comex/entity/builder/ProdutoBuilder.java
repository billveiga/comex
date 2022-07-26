package br.com.alura.comex.entity.builder;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Produto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;

public class ProdutoBuilder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String descricao;

    private BigDecimal precoUnitario;

    private Integer quantidadeEstoque;

    private Categoria categoria;

    public ProdutoBuilder id() {
        return this;
    }

    public ProdutoBuilder name(String nome) {
        this.name = nome;
        return this;
    }

    public ProdutoBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ProdutoBuilder precoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        return this;
    }

    public ProdutoBuilder quantidadeEstoque(Integer quantidade) {
        this.quantidadeEstoque = quantidade;
        return this;
    }

    public ProdutoBuilder categoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public Produto build() {
        return new Produto(name, descricao, precoUnitario, quantidadeEstoque, categoria);
    }


}
