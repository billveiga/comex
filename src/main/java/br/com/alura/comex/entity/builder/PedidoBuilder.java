package br.com.alura.comex.entity.builder;

import br.com.alura.comex.entity.Cliente;
import br.com.alura.comex.entity.ItemDePedido;
import br.com.alura.comex.entity.Pedido;
import br.com.alura.comex.entity.TipoDesconto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoBuilder {

    private LocalDate data = LocalDate.now();

    private Cliente cliente;


    private BigDecimal desconto;

    private List<TipoDesconto> tipoDesconto;

    public PedidoBuilder data(){
        return this;
    }

    public PedidoBuilder cliente(Cliente cliente){
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder desconto(BigDecimal desconto){
        this.desconto = desconto;
        return this;
    }

    public PedidoBuilder tipoDesconto(List<TipoDesconto> tipoDesconto){
        this.tipoDesconto = tipoDesconto;
        return this;
    }

    public Pedido build(){
        return new Pedido(cliente, desconto, tipoDesconto);
    }
}
