package br.com.alura.comex.compartilhado.infra.pedido;

import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import br.com.alura.comex.compartilhado.entity.categoria.RelatorioPedidosPorCategoriaProjecao;
import br.com.alura.comex.compartilhado.entity.pedido.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {


    private final PedidoDAO pedidoDAO;

    @Override
    public Page<Pedido> listarTodosPedidos(Pageable pageable) {
        Page<PedidoEntity> pedidoEntity = pedidoDAO.findAll(pageable);
        return pedidoEntity.map(PedidoEntity::paraPedido);
    }

    @Override
    public Pedido buscarPedidoPorCodIdentificador(Long codigoIdentificador) {
        PedidoEntity pedidoEntity = pedidoDAO.findById(codigoIdentificador)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido com código %s não encontrado! ".formatted(codigoIdentificador)));
        return pedidoEntity.paraPedido();
    }

    @Override
    public Pedido cadastrarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoDAO.save(PedidoEntity.converter(pedido));
        return pedidoEntity.paraPedido();
    }

    @Override
    public List<RelatorioPedidosPorCategoriaProjecao> buscarPedidosPorCategoria() {
        return pedidoDAO.findPedidosPorCategoria();
    }
}
