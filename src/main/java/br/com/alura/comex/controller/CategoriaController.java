package br.com.alura.comex.controller;


import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.dto.DetalhesDaCategoriaDto;
import br.com.alura.comex.controller.form.AtualizacaoCategoriaForm;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.projecao.RelatorioPedidosPorCategoriaProjecao;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(CategoriaDto.converter(categorias));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriComponentsBuilder) {

        Categoria categoria = form.converter();
        categoriaRepository.save(categoria);

        URI uri = uriComponentsBuilder.path("/api/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDaCategoriaDto> buscarCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            return ResponseEntity.ok().body(new DetalhesDaCategoriaDto(categoria.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCategoriaForm form) {
        Optional<Categoria> optional = categoriaRepository.findById(id);

        if (optional.isPresent()) {
            Categoria categoria = form.atualizar(id, categoriaRepository);
            return ResponseEntity.ok(new CategoriaDto(categoria));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {

        Optional<Categoria> optional = categoriaRepository.findById(id);

        if (optional.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<RelatorioPedidosPorCategoriaProjecao>> pedidosVendidosPorCategoria(){

        List<RelatorioPedidosPorCategoriaProjecao> relatorioPedidosPorCategoria = pedidoRepository.findPedidosPorCategoria();

        return ResponseEntity.ok(relatorioPedidosPorCategoria);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizarStatus(@PathVariable Long id){
        Optional<Categoria> optional = categoriaRepository.findById(id);
        AtualizacaoCategoriaForm atualizacaoCategoriaForm = new AtualizacaoCategoriaForm();

        if (optional.isPresent()){
            Categoria categoria = atualizacaoCategoriaForm.atualizarStatus(optional.get());
            return ResponseEntity.ok(new CategoriaDto(categoria));
        }

        return ResponseEntity.notFound().build();

    }

}
