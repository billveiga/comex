package br.com.alura.comex.estoque.infra.produto;

import br.com.alura.comex.compartilhado.infra.produto.DimensaoEntity;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoqueRepository;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProdutoEstoqueRepositoryImpl implements ProdutoEstoqueRepository {

    private final ProdutoEstoqueDAO produtoEstoqueDAO;

    @Override
    public Page<ProdutoEstoque> listarProdutosCadastradosPaginados(Pageable pegeable) {
        return produtoEstoqueDAO.findAll(pegeable).map(ProdutoEstoqueEntity::paraProduto);
    }

    @Override
    public List<ProdutoEstoque> listarTodosProdutosCadastrados() {
        List<ProdutoEstoqueEntity> produtoEntities = produtoEstoqueDAO.findAll();
        return produtoEntities.stream().map(ProdutoEstoqueEntity::paraProduto).toList();
    }

    @Override
    public ProdutoEstoque buscarProdutoPorCodProduto(Long codigoProduto) {

        ProdutoEstoqueEntity produtoEstoqueEntity = produtoEstoqueDAO.findById(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com código %s não encontrado no estoque".formatted(codigoProduto)));

        return produtoEstoqueEntity.paraProduto();
    }

    @Override
    public ProdutoEstoque cadastrarProduto(ProdutoEstoque produtoEstoque) {
        ProdutoEstoqueEntity produtoEstoqueEntity = produtoEstoqueDAO.save(ProdutoEstoqueEntity.converter(produtoEstoque));
        return produtoEstoqueEntity.paraProduto();
    }

    @Override
    public ProdutoEstoque atualizarProduto(Long codigoProduto, ProdutoEstoque produtoEstoque) {

        ProdutoEstoqueEntity produtoEstoqueEntity = produtoEstoqueDAO.findById(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado no estoque %s".formatted(produtoEstoque.getNome())));

        produtoEstoqueEntity.setNome(produtoEstoque.getNome());
        produtoEstoqueEntity.setDescricao(produtoEstoque.getDescricao());
        produtoEstoqueEntity.setPrecoUnitario(produtoEstoque.getPrecoUnitario());
        produtoEstoqueEntity.setQuantidadeEstoque(produtoEstoque.getQuantidadeEstoque());
        produtoEstoqueEntity.setCategoria(CategoriaEntity.converter(produtoEstoque.getCategoria()));
        produtoEstoqueEntity.setDimensao(DimensaoEntity.converter(produtoEstoque.getDimensao()));

        return produtoEstoqueEntity.paraProduto();
    }

    @Override
    public void excluirProduto(Long codigoProduto) {

        ProdutoEstoqueEntity produto = produtoEstoqueDAO.findById(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado no estoque"));

        produtoEstoqueDAO.deleteById(produto.getCodigoProduto());
    }
}
