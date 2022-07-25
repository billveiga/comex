package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.FormatosImpressao;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioProdutoMaisCaroCategoria extends GeradorRelatorio {
    public Map<String, String> getProdutoMaisCaroCategoria(List<Pedido> listaDePedidos) throws IllegalArgumentException {
        Map<String, String> produtoMaisCaroCategoria = new TreeMap<>();

        listaDePedidos.stream().collect(Collectors.groupingBy(Pedido::getCategoria)).forEach((a, b) -> {
            produtoMaisCaroCategoria.put(a, b.stream().max(Comparator.comparing(Pedido::getPreco))
                    .orElseThrow(() -> new IllegalStateException("Não foi possível encontrar o produto mais caro da Categoria: " + a)).getProduto());
        });
        return produtoMaisCaroCategoria;
    }

    public Map<String, BigDecimal> getMaiorPrecoPorCategoria(List<Pedido> listaDePedidos) {
        Map<String, BigDecimal> maiorPrecoPorCategoria = new TreeMap<>();
        listaDePedidos.stream().collect(Collectors.groupingBy(Pedido::getCategoria)).forEach((a, b) -> {
            maiorPrecoPorCategoria.put(a, b.stream().max(Comparator.comparing(Pedido::getPreco))
                    .orElseThrow(() -> new IllegalStateException("Não foi possível encontrar o maior valor Categoria: " + a)).getPreco());
        });
        return maiorPrecoPorCategoria;
    }


    @Override
    protected String gerarCabecalho() {
        return "\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA";
    }

    @Override
    protected String gerarConteudo(List<Pedido> listaDePedidos) {

        StringBuilder conteudo = new StringBuilder();

        this.getProdutoMaisCaroCategoria(listaDePedidos).forEach((a, b) -> {
            conteudo.append("\nCATEGORIA: " + a + "\nPRODUTO: " + b);
            conteudo.append("\nPREÇO: " + FormatosImpressao.getRealFormat(this.getMaiorPrecoPorCategoria(listaDePedidos).get(a)) + "\n");
        });

        return conteudo.toString();
    }
}
