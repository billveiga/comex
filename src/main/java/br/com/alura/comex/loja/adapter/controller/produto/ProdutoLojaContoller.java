package br.com.alura.comex.loja.adapter.controller.produto;


import br.com.alura.comex.compartilhado.entity.categoria.CategoriaRepository;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaRepositoryImpl;
import br.com.alura.comex.loja.adapter.controller.produto.dto.DetalhesDeProdutoLojaDto;
import br.com.alura.comex.loja.adapter.controller.produto.dto.ProdutoLojaDto;
import br.com.alura.comex.loja.adapter.controller.produto.form.AtualizacaoProdutoLojaForm;
import br.com.alura.comex.loja.adapter.controller.produto.form.ProdutoLojaForm;
import br.com.alura.comex.loja.entity.produto.ProdutoLoja;
import br.com.alura.comex.loja.entity.produto.ProdutoLojaRepository;
import br.com.alura.comex.loja.infra.produto.ProdutoLojaEntity;
import br.com.alura.comex.loja.infra.produto.ProdutoLojaRepositoryImpl;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/loja/produtos")
public class ProdutoLojaContoller {

    private final ProdutoLojaRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoLojaDto>> listar(@RequestParam(defaultValue = "0") int pagina) {

        Pageable pegeable = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "nome"));

        Page<ProdutoLoja> produtos = produtoRepository.listarProdutosCadastradosPaginados(pegeable);

        Page<ProdutoLojaDto> produtosDto = ProdutoLojaDto.converterPagina(produtos);

        return ResponseEntity.ok().body(produtosDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoLojaDto> cadastrar(@RequestBody @Valid ProdutoLojaForm form, UriComponentsBuilder uriBuilder) {

        ProdutoLoja prod = form.converterEmProdutoLoja(categoriaRepository);
        ProdutoLoja produto = produtoRepository.cadastrarProduto(prod);

        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getCodigoProduto()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoLojaDto(produto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDeProdutoLojaDto> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoLoja produto = produtoRepository.buscarProdutoPorCodProduto(id);
        return ResponseEntity.ok(new DetalhesDeProdutoLojaDto(ProdutoLojaEntity.converter(produto)));

    }


    @PutMapping("/{codigoProduto}")
    @Transactional
    public ResponseEntity<ProdutoLojaDto> atualizar(@PathVariable Long codigoProduto, @RequestBody @Valid AtualizacaoProdutoLojaForm form) {

        ProdutoLoja produto = produtoRepository.atualizarProduto(codigoProduto, form.converterEmProdutoLoja(categoriaRepository));

        return ResponseEntity.ok(new ProdutoLojaDto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        produtoRepository.excluirProduto(id);

        return ResponseEntity.ok().build();
    }


}
