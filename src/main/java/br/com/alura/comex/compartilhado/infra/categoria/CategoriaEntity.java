package br.com.alura.comex.compartilhado.infra.categoria;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import br.com.alura.comex.estoque.infra.produto.ProdutoEstoqueEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCategoria status = StatusCategoria.ATIVA;

    @OneToMany(mappedBy = "categoria")
    private List<ProdutoEstoqueEntity> produtoEntities = new ArrayList<>();


    public static CategoriaEntity converter(Categoria categoria) {

        return CategoriaEntity.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .status(categoria.getStatus())
                .produtoEntities(categoria.getProdutoEstoques().stream().map(ProdutoEstoqueEntity::converter).toList())
                .build();
    }

    public List<ProdutoEstoque> toProdutos() {
        return this.produtoEntities.stream().map(ProdutoEstoqueEntity::paraProduto).toList();
    }

    public Categoria toCategoria() {
        return Categoria.builder()
                .id(this.id)
                .nome(this.nome)
                .status(this.status)
                .produtoEstoques(new ArrayList<>())
                .build();
    }


    public void adicionarProduto(ProdutoEstoqueEntity produtoEstoqueEntity) {
        produtoEstoqueEntity.setCategoria(this);
        this.produtoEntities.add(produtoEstoqueEntity);
    }

}