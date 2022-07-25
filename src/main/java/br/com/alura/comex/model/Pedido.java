package br.com.alura.comex.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Pedido {
    @CsvBindByName(column = "categoria", required = true)
    private String categoria;
    @CsvBindByName(column = "produto", required = true)
    private String produto;
    @CsvBindByName(column = "preco", required = true)
    private BigDecimal preco;
    @CsvBindByName(column = "quantidade", required = true)
    private int quantidade;
    @CsvBindByName(column = "data", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate data;
    @CsvBindByName(column = "cliente", required = true)
    private String cliente;


    public BigDecimal getValorTotal() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
