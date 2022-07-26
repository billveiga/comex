package br.com.alura.comex.compartilhado.adapter.controller.pedido;

import br.com.alura.comex.compartilhado.adapter.controller.pedido.dto.DetalhamentoPedidoDto;
import br.com.alura.comex.compartilhado.adapter.controller.pedido.dto.DetalhesPedidoDto;
import br.com.alura.comex.compartilhado.adapter.controller.pedido.dto.PedidoDto;
import br.com.alura.comex.compartilhado.adapter.controller.pedido.form.PedidoFrom;
import br.com.alura.comex.compartilhado.entity.cliente.ClienteRepository;
import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import br.com.alura.comex.compartilhado.entity.pedido.PedidoRepository;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoEstoqueRepository produtoRepository;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar(@PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC, sort = "data") Pageable pageable) {

        Page<Pedido> pedidos = pedidoRepository.listarTodosPedidos(pageable);
        Page<PedidoDto> paginaPedidos = PedidoDto.converter(pedidos);

        return ResponseEntity.ok().body(paginaPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoPedidoDto> buscarPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.buscarPedidoPorCodIdentificador(id);

        return ResponseEntity.ok().body(new DetalhamentoPedidoDto(pedido));
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "relatorioPedidosPorCategoria", allEntries = true)
    public ResponseEntity<DetalhesPedidoDto> cadastrar(@RequestBody @Valid PedidoFrom form, UriComponentsBuilder uriComponentsBuilder) {

        Pedido pedido = pedidoRepository.cadastrarPedido(form.converter(clienteRepository, produtoRepository));

        URI uri = uriComponentsBuilder.path("/api/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPedidoDto(pedido));

    }
}
