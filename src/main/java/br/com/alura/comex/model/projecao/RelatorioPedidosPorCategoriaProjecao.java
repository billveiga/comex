package br.com.alura.comex.model.projecao;

import java.math.BigDecimal;

public interface RelatorioPedidosPorCategoriaProjecao {
    String getNome();
    Long getQuantidadeProdutos();
    BigDecimal getMontanteVendido();
}
