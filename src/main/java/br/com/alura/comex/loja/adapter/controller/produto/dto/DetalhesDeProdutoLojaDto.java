package br.com.alura.comex.loja.adapter.controller.produto.dto;

import br.com.alura.comex.loja.infra.produto.ProdutoLojaEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalhesDeProdutoLojaDto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private String categoria;

    private int comprimento;

    private int altura;

    private int largura;

    private BigDecimal peso;

    public DetalhesDeProdutoLojaDto(ProdutoLojaEntity produtoLojaEntity) {
        this.id = produtoLojaEntity.getCodigoProduto();
        this.nome = produtoLojaEntity.getNome();
        this.descricao = produtoLojaEntity.getDescricao();
        this.precoUnitario = produtoLojaEntity.getPrecoUnitario();
        this.quantidadeEstoque = produtoLojaEntity.getQuantidadeEstoque();
        this.categoria = produtoLojaEntity.getCategoria().getNome();
        this.comprimento = produtoLojaEntity.getDimensao().getComprimento();
        this.altura = produtoLojaEntity.getDimensao().getAltura();
        this.largura = produtoLojaEntity.getDimensao().getLargura();
        this.peso = produtoLojaEntity.getDimensao().getPeso();
    }


}
