package br.com.alura.comex.estoque.adapter.controller.produto.form;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import br.com.alura.comex.compartilhado.entity.categoria.CategoriaRepository;
import br.com.alura.comex.compartilhado.entity.produto.Dimensao;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaRepositoryImpl;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter @Setter
public class AtualizacaoProdutoEstoqueForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    private String descricao;
    @NotNull
    private BigDecimal precoUnitario;

    private int quantidadeEstoque;
    @NotNull
    private Long categoria;

    private int comprimento;

    private int altura;

    private int largura;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal peso;

    public ProdutoEstoque paraProduto(CategoriaRepository repository){

        Categoria categoria = repository.buscarCategoria(this.categoria);

        return ProdutoEstoque.builder()
                .nome(this.nome)
                .descricao(this.descricao)
                .precoUnitario(this.precoUnitario)
                .quantidadeEstoque(this.quantidadeEstoque)
                .categoria(categoria)
                .dimensao(Dimensao.builder()
                        .comprimento(this.comprimento)
                        .altura(this.altura)
                        .largura(this.largura)
                        .peso(this.peso)
                        .build())
                .build();
    }
}