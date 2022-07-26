package br.com.alura.comex.loja.infra.produto;

import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import br.com.alura.comex.compartilhado.infra.produto.DimensaoEntity;
import br.com.alura.comex.loja.entity.produto.ProdutoLoja;
import br.com.alura.comex.loja.entity.produto.ProdutoLojaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class ProdutoLojaRepositoryImpl implements ProdutoLojaRepository {

    private final ProdutoLojaDAO produtoLojaDAO;

    @Override
    public ProdutoLoja buscarProdutoPorCodProduto(Long codigoProduto) {
        ProdutoLojaEntity produtoEntity = produtoLojaDAO.findById(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com código %s não encontrado na loja".formatted(codigoProduto)));

        return produtoEntity.paraProdutoLoja();
    }

    @Override
    public ProdutoLoja cadastrarProduto(ProdutoLoja produto) {
        ProdutoLojaEntity produtoEntity = produtoLojaDAO.save(ProdutoLojaEntity.converter(produto));
        return produtoEntity.paraProdutoLoja();
    }

    @Override
    public ProdutoLoja atualizarProduto(Long codigoProduto, ProdutoLoja produto) {
        ProdutoLojaEntity produtoEntity = produtoLojaDAO.findById(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto %s não encontrado na loja".formatted(produto.getNome())));

        produtoEntity.setNome(produto.getNome());
        produtoEntity.setDescricao(produto.getDescricao());
        produtoEntity.setPrecoUnitario(produto.getPrecoUnitario());
        produtoEntity.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        produtoEntity.setCategoria(CategoriaEntity.converter(produto.getCategoria()));
        produtoEntity.setDimensao(DimensaoEntity.converter(produto.getDimensao()));

        return produtoEntity.paraProdutoLoja();
    }

    @Override
    public void excluirProduto(Long codigoProduto) {

        ProdutoLojaEntity produtoEntity = produtoLojaDAO.findById(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado na loja"));

        produtoLojaDAO.deleteById(produtoEntity.getCodigoProduto());

    }

    @Override
    public Page<ProdutoLoja> listarProdutosCadastradosPaginados(Pageable pegeable) {
        return produtoLojaDAO.findAll(pegeable).map(ProdutoLojaEntity::paraProdutoLoja);
    }
}
