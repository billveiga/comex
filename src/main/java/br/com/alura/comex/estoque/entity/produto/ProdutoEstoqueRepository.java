package br.com.alura.comex.estoque.entity.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

    public interface ProdutoEstoqueRepository {

        List<ProdutoEstoque> listarTodosProdutosCadastrados();

        ProdutoEstoque buscarProdutoPorCodProduto(Long codigoProduto);

        ProdutoEstoque cadastrarProduto(ProdutoEstoque produtoEstoque);

        ProdutoEstoque atualizarProduto(Long codigoProduto, ProdutoEstoque produtoEstoque);

        void excluirProduto(Long codigoProduto);

        Page<ProdutoEstoque> listarProdutosCadastradosPaginados(Pageable pegeable);
}
