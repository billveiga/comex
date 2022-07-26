package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return NomeCliente;
    }

    public List<ItemDePedidoDto> getItens() {
        return itens;
    }
}
