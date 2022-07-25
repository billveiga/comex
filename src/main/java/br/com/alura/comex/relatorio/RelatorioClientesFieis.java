package br.com.alura.comex.relatorio;

import br.com.alura.comex.menu.OpcoesEscolha;
import br.com.alura.comex.model.Pedido;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioClientesFieis extends GeradorRelatorio{

    @Override
    protected String gerarCabecalho() {
        return "\n#### RELATÓRIO DE CLIENTES FIÉIS";
    }

    @Override
    protected String gerarConteudo(List<Pedido> listaDePedidos) {
        StringBuilder conteudo = new StringBuilder();

        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()))
                .forEach((cliente, c) -> conteudo.append("\nNOME: " + cliente + "\nNº DE PEDIDOS: " + c + "\n"));

        return conteudo.toString();
    }

}
