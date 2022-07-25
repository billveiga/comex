package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;

import java.util.List;

public interface GeradorRelatorio {
    String gerarCabecalho();
    String gerarConteudo(List<Pedido> listaDePedidos);
}