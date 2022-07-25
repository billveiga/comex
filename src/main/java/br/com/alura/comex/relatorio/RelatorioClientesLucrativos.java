package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.FormatosImpressao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioClientesLucrativos implements GeradorRelatorio {

    private Map<String, BigDecimal> getClientesLucrativos(List<Pedido> listaDePedidos) {
        Map<String, BigDecimal> clientesLucrativos = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente))
                .forEach((a, v) -> {
                    clientesLucrativos.put(a, v.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
                });

        return clientesLucrativos;
    }

    private Map<String, Long> quantidadePedidoClienteMaisLucrativo(List<Pedido> listaDePedidos) {


        return listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()));
    }

    @Override
    public String gerarCabecalho() {
        return "\n#### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS";
    }

    @Override
    public String gerarConteudo(List<Pedido> listaDePedidos) {
        if ( listaDePedidos.isEmpty() || listaDePedidos == null) throw new IllegalArgumentException("Lista de pedidos vazia!");

        StringBuilder conteudo = new StringBuilder();

        this.getClientesLucrativos(listaDePedidos)
                .entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(2)
                .forEach(a -> {
                    conteudo.append("\nCLIENTE: " + a.getKey());
                    conteudo.append("\nNº PEDIDOS: " + quantidadePedidoClienteMaisLucrativo(listaDePedidos).get(a.getKey()));
                    conteudo.append("\nMONTANTE GASTO: " + FormatosImpressao.getRealFormat(a.getValue()) + "\n");
                });

        return conteudo.toString();
    }

}
