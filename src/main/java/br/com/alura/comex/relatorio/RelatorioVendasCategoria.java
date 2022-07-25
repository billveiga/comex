package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.FormatosImpressao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioVendasCategoria extends GeradorRelatorio{

    private Map<String, BigDecimal> getMontantePorCategoria(List<Pedido> listaDePedidos){

        Map<String, BigDecimal> montantePorCategoria = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria))
                .forEach((a,v) -> montantePorCategoria.put(a, v.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add)));
        return montantePorCategoria;

    }

    private Map<String, Integer> getQtdProdutosPorCategoria(List<Pedido> listaDePedidos){

        Map<String, Integer>  qtdProdutosPorCategoria = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria))
                .forEach((a,v) -> qtdProdutosPorCategoria.put(a,v.stream().mapToInt(Pedido::getQuantidade).sum()));
        return qtdProdutosPorCategoria;

    }
    @Override
    protected String gerarCabecalho() {
        return "\n#### RELATÓRIO DE VENDAS POR CATEGORIA";
    }

    @Override
    protected String gerarConteudo(List<Pedido> listaDePedidos) {

        StringBuilder conteudo  = new StringBuilder();

        this.getQtdProdutosPorCategoria(listaDePedidos).forEach((catg, qtd) -> {
            conteudo.append("\nCATEGORIA: " + catg + "\nQUANTIDADE VENDIDA: " + qtd);
            conteudo.append("\nMONTANTE: " +  FormatosImpressao.getRealFormat(this.getMontantePorCategoria(listaDePedidos).get(catg)) + "\n");
        });
        return conteudo.toString();
    }

}
