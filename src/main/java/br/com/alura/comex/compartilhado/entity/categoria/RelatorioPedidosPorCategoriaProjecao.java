package br.com.alura.comex.compartilhado.entity.categoria;

import java.math.BigDecimal;

public interface RelatorioPedidosPorCategoriaProjecao {
    String getNome();
    Long getQuantidadeProdutos();
    BigDecimal getMontanteVendido();
}
