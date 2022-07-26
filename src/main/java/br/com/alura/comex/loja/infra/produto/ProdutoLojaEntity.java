package br.com.alura.comex.loja.infra.produto;


import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import br.com.alura.comex.compartilhado.infra.produto.DimensaoEntity;
import br.com.alura.comex.loja.entity.produto.ProdutoLoja;
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
@Table(name = "produtos_loja")
public class ProdutoLojaEntity {

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

    public static ProdutoLojaEntity converter(ProdutoLoja produto) {
        return ProdutoLojaEntity.builder()
                .codigoProduto(produto.getCodigoProduto())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .precoUnitario(produto.getPrecoUnitario())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .categoria(CategoriaEntity.converter(produto.getCategoria()))
                .dimensao(DimensaoEntity.converter(produto.getDimensao()))
                .build();
    }

    public ProdutoLoja paraProdutoLoja() {
        return ProdutoLoja.builder()
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
