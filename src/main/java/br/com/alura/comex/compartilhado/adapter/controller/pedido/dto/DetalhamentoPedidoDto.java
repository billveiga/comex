package br.com.alura.comex.compartilhado.adapter.controller.pedido.dto;

import br.com.alura.comex.compartilhado.entity.pedido.Pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhamentoPedidoDto {
    private LocalDate data;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private List<ItemDePedidoDto> itens;

    private Long idCliente;

    private String nomeCliente;

    public DetalhamentoPedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotalPedido();
        this.desconto = pedido.getDesconto();
        this.itens = pedido.getItens().stream().map(ItemDePedidoDto::new).collect(Collectors.toList());
        this.idCliente = pedido.getCliente().getId();
        this.nomeCliente = pedido.getCliente().getNome();
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public List<ItemDePedidoDto> getItens() {
        return itens;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }
}
