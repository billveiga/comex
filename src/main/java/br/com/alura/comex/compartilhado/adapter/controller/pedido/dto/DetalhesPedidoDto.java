package br.com.alura.comex.compartilhado.adapter.controller.pedido.dto;

import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DetalhesPedidoDto {

    private LocalDate data;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private int quantidadeProdutos;

    private Long idCliente;

    private String NomeCliente;

    private List<ItemDePedidoDto> itens;

    public DetalhesPedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotalPedido();
        this.desconto = pedido.getDesconto();
        this.quantidadeProdutos = pedido.getQuantidadeDeProdutos();
        this.idCliente = pedido.getCliente().getId();
        NomeCliente = pedido.getCliente().getNome();
        this.itens = pedido.getItens().stream().map(ItemDePedidoDto::new).collect(Collectors.toList());
    }
}
