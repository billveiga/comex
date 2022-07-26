package br.com.alura.comex.estoque.adapter.controller.produto;

import br.com.alura.comex.compartilhado.entity.categoria.CategoriaRepository;
import br.com.alura.comex.estoque.adapter.controller.produto.dto.DetalhesDeProdutoEstoqueDto;
import br.com.alura.comex.estoque.adapter.controller.produto.dto.ProdutoEstoqueDto;
import br.com.alura.comex.estoque.adapter.controller.produto.form.AtualizacaoProdutoEstoqueForm;
import br.com.alura.comex.estoque.adapter.controller.produto.form.ProdutoEstoqueForm;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoqueRepository;
import br.com.alura.comex.estoque.infra.produto.ProdutoEstoqueEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoEstoqueController {

    private final ProdutoEstoqueRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoEstoqueDto>> listar(@RequestParam(defaultValue = "0") int pagina) {

        Pageable pegeable = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "nome"));

        Page<ProdutoEstoque> produtos = produtoRepository.listarProdutosCadastradosPaginados(pegeable);

        Page<ProdutoEstoqueDto> produtosDto = ProdutoEstoqueDto.converterPagina(produtos);

        return ResponseEntity.ok().body(produtosDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoEstoqueDto> cadastrar(@RequestBody @Valid ProdutoEstoqueForm form, UriComponentsBuilder uriBuilder) {

        ProdutoEstoque prod = form.converterEmProduto(categoriaRepository);
        ProdutoEstoque produtoEstoque = produtoRepository.cadastrarProduto(prod);

        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produtoEstoque.getCodigoProduto()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoEstoqueDto(produtoEstoque));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDeProdutoEstoqueDto> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoEstoque produtoEstoque = produtoRepository.buscarProdutoPorCodProduto(id);
        return ResponseEntity.ok(new DetalhesDeProdutoEstoqueDto(ProdutoEstoqueEntity.converter(produtoEstoque)));

    }


    @PutMapping("/{codigoProduto}")
    @Transactional
    public ResponseEntity<ProdutoEstoqueDto> atualizar(@PathVariable Long codigoProduto, @RequestBody @Valid AtualizacaoProdutoEstoqueForm form) {

        ProdutoEstoque produtoEstoque = produtoRepository.atualizarProduto(codigoProduto, form.paraProduto(categoriaRepository));

        return ResponseEntity.ok(new ProdutoEstoqueDto(produtoEstoque));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        produtoRepository.excluirProduto(id);

        return ResponseEntity.ok().build();
    }
}
