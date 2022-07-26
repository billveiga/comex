package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Pedido;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDto {

    private LocalDate data;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private int quantidadeProdutos;

    private Long idCliente;

    private String nomeCliente;

    public PedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotalPedido();
        this.desconto = pedido.getValorTotalDescontos();
        this.quantidadeProdutos = pedido.getQuantidadeDeProdutos();
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

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public static Page<PedidoDto> converter(Page<Pedido> pedidosDb) {
        return pedidosDb.map(PedidoDto::new);
    }
}
