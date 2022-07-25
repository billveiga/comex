package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioProdutosMaisVendidos implements GeradorRelatorio {

    private Map<String, Integer> getProdutosMaisVendidos(List<Pedido> listaDePedidos) {
        Map<String, Integer> produtosMaisVendidos = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getProduto))
                .forEach((a, v) -> produtosMaisVendidos.put(a, v.stream().mapToInt(Pedido::getQuantidade).sum()));
        return produtosMaisVendidos;
    }

    @Override
    public String gerarCabecalho() {
        return "\n#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS";
    }

    @Override
    public String gerarConteudo(List<Pedido> listaDePedidos) {

        if ( listaDePedidos.isEmpty() || listaDePedidos == null) throw new IllegalArgumentException("Lista de pedidos vazia!");

        StringBuilder conteudo = new StringBuilder();

        this.getProdutosMaisVendidos(listaDePedidos)
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(a -> conteudo.append("\nPRODUTO: " + a.getKey() + "\nQUANTIDADE: " + a.getValue() + "\n"));

        return conteudo.toString();
    }

}
