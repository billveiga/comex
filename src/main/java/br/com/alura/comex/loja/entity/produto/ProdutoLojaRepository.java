package br.com.alura.comex.loja.entity.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoLojaRepository {

    ProdutoLoja buscarProdutoPorCodProduto(Long codigoProduto);

    ProdutoLoja cadastrarProduto(ProdutoLoja produto);

    ProdutoLoja atualizarProduto(Long codigoProduto, ProdutoLoja produto);

    void excluirProduto(Long codigoProduto);

    Page<ProdutoLoja> listarProdutosCadastradosPaginados(Pageable pegeable);
}
