package br.com.alura.comex.loja.adapter.controller.produto.dto;

import br.com.alura.comex.compartilhado.adapter.controller.categoria.dto.CategoriaDto;
import br.com.alura.comex.loja.entity.produto.ProdutoLoja;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProdutoLojaDto {

    private Long codigoProduto;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private CategoriaDto categoria;

    private int comprimento;

    private int altura;

    private int largura;

    private BigDecimal peso;

    public ProdutoLojaDto(ProdutoLoja produtoLoja) {
        this.codigoProduto = produtoLoja.getCodigoProduto();
        this.nome = produtoLoja.getNome();
        this.descricao = produtoLoja.getDescricao();
        this.precoUnitario = produtoLoja.getPrecoUnitario();
        this.quantidadeEstoque = produtoLoja.getQuantidadeEstoque();
        this.categoria = new CategoriaDto(produtoLoja.getCategoria());
        this.comprimento = produtoLoja.getDimensao().getComprimento();
        this.altura = produtoLoja.getDimensao().getAltura();
        this.largura = produtoLoja.getDimensao().getLargura();
        this.peso = produtoLoja.getDimensao().getPeso();
    }

    public static Page<ProdutoLojaDto> converterPagina(Page<ProdutoLoja> produtos) {
        return produtos.map(ProdutoLojaDto::new);
    }
}
