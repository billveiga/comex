package br.com.alura.comex.model.builder;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoBuilder {

    private String categoria;
    private String produto;
    private BigDecimal preco;
    private int quantidade;
    private LocalDate data;
    private String cliente;

    public PedidoBuilder categoria(String categoria){
        this.categoria = categoria;
        return this;
    }
    public PedidoBuilder produto(String produto){
        this.produto = produto;
        return this;
    }
    public PedidoBuilder preco(BigDecimal preco){
        this.preco = preco;
        return this;
    }
    public PedidoBuilder quantidade(int quantidade){
        this.quantidade = quantidade;
        return this;
    }
    public PedidoBuilder data(LocalDate data){
        this.data = data;
        return this;
    }
    public PedidoBuilder cliente(String cliente){
        this.cliente = cliente;
        return this;
    }

    public Pedido build(){
        return new Pedido(categoria, produto, preco, quantidade, data, cliente);
    }
}
