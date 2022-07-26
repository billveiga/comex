package br.com.alura.comex.compartilhado.adapter.controller.pedido.form;
import br.com.alura.comex.compartilhado.entity.cliente.ClienteRepository;
import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import br.com.alura.comex.compartilhado.entity.cliente.Cliente;
import br.com.alura.comex.compartilhado.infra.cliente.ClienteRepositoryImpl;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoqueRepository;
import br.com.alura.comex.estoque.infra.produto.ProdutoEstoqueRepositoryImpl;
import br.com.alura.comex.compartilhado.usecase.Descontos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoFrom {

    @NotNull
    @Min(0)
    private Long idCliente;

    @NotNull
    private List<ItemDePedidoForm> itens;

    public Pedido converter(ClienteRepository clienteRepository, ProdutoEstoqueRepository produtoRepository) {
         Cliente cliente = clienteRepository.buscarPorId(this.idCliente);

         Pedido pedido = new Pedido(cliente);

         cliente.adicionarPedido(pedido);

         this.itens.forEach(p -> {
             pedido.adicionarItem(p.converter(produtoRepository));
         });

        Descontos.aplicarDesconto(pedido);

        return pedido;
    }
}
