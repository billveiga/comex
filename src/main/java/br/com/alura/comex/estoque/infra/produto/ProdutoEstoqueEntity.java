package br.com.alura.comex.estoque.infra.produto;

import br.com.alura.comex.compartilhado.infra.produto.DimensaoEntity;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "produtos_estoque")
public class ProdutoEstoqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoProduto;
    @Column(nullable = false)
    private String nome;
    private String descricao;
    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;
    @Column(name = "quantidade_estoque", nullable = false)
    @Min(0)
    private int quantidadeEstoque;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriaEntity categoria;

    @Embedded
    private DimensaoEntity dimensao;

    public static ProdutoEstoqueEntity converter(ProdutoEstoque produtoEstoque) {

        return ProdutoEstoqueEntity.builder()
                .nome(produtoEstoque.getNome())
                .codigoProduto(produtoEstoque.getCodigoProduto())
                .descricao(produtoEstoque.getDescricao())
                .precoUnitario(produtoEstoque.getPrecoUnitario())
                .quantidadeEstoque(produtoEstoque.getQuantidadeEstoque())
                .categoria(CategoriaEntity.converter(produtoEstoque.getCategoria()))
                .dimensao(DimensaoEntity.converter(produtoEstoque.getDimensao()))
                .build();
    }

    public ProdutoEstoque paraProduto() {

        return ProdutoEstoque.builder()
                .codigoProduto(this.codigoProduto)
                .nome(this.nome)
                .descricao(this.descricao)
                .precoUnitario(this.precoUnitario)
                .quantidadeEstoque(this.quantidadeEstoque)
                .categoria(this.categoria.toCategoria())
                .dimensao(this.dimensao.paraDimensao())
                .build();
    }
}
