package br.com.alura.comex.compartilhado.adapter.controller.pedido.form;

import br.com.alura.comex.compartilhado.entity.itemDePedido.ItemDePedido;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoqueRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
public class ItemDePedidoForm {

    @NotNull
    @Min(0)
    @Valid
    private Long idProduto;

    @Min(1)
    private int quantidadeProduto;

    private ProdutoEstoque verificarProduto(ProdutoEstoqueRepository produtoRepository) {
        ProdutoEstoque produtoEstoque = produtoRepository.buscarProdutoPorCodProduto(this.idProduto);

        if (produtoEstoque.getQuantidadeEstoque() < this.quantidadeProduto) {
            throw new RuntimeException("Sem produto em estoque");
        }

        produtoEstoque.setQuantidadeEstoque(
                produtoEstoque.getQuantidadeEstoque() - this.quantidadeProduto
        );

        return produtoEstoque;
    }

    public ItemDePedido converter(ProdutoEstoqueRepository produtoRepository) {
        return new ItemDePedido(this.quantidadeProduto, verificarProduto(produtoRepository));
    }

}
