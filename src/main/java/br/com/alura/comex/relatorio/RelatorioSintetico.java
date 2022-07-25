package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.FormatosImpressao;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class RelatorioSintetico implements GeradorRelatorio {

    @Override
    public String gerarCabecalho() {
        return "\n#### RELATÓRIO DE VALORES TOTAIS";
    }

    @Override
    public String gerarConteudo(List<Pedido> listaDePedidos) {

        StringBuilder conteudo = new StringBuilder();

        conteudo.append("- TOTAL DE PEDIDOS REALIZADOS: " + this.getTotalDePedidosRealizados(listaDePedidos) + "\n");
        conteudo.append("- TOTAL DE PRODUTOS VENDIDOS: " + this.getTotalDeProdutosVendidos(listaDePedidos) + "\n");
        conteudo.append("- TOTAL DE CATEGORIAS: " + this.getTotalDeCategorias(listaDePedidos) + "\n");
        conteudo.append("- MONTANTE DE VENDAS: " + FormatosImpressao.getRealFormat(this.getMontanteDeVendas(listaDePedidos)) + "\n");
        conteudo.append("- PEDIDO MAIS BARATO: " + FormatosImpressao.getRealFormat(this.getPedidoMaisBarato(listaDePedidos).getValorTotal()) + " (" + this.getPedidoMaisBarato(listaDePedidos).getProduto() + ")" + "\n");
        conteudo.append("- PEDIDO MAIS CARO: " + FormatosImpressao.getRealFormat(this.getPedidoMaisCaro(listaDePedidos).getValorTotal()) + " (" + this.getPedidoMaisCaro(listaDePedidos).getProduto() + ")" + "\n");

        return conteudo.toString();
    }


    public BigDecimal getMontanteDeVendas(List<Pedido> listaDePedidos) {
        return listaDePedidos.stream()
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Pedido getPedidoMaisBarato(List<Pedido> listaDePedidos) {
        return listaDePedidos.stream()
                .min(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException("Sem valores para comparação"));
    }

    public Pedido getPedidoMaisCaro(List<Pedido> listaDePedidos) {
        return listaDePedidos.stream()
                .max(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException("Sem valores para comparação"));
    }

    public int getTotalDePedidosRealizados(List<Pedido> listaDePedidos) {
        return listaDePedidos.size();
    }

    public int getTotalDeProdutosVendidos(List<Pedido> listaDePedidos) {
        return listaDePedidos.stream()
                .mapToInt(Pedido::getQuantidade)
                .sum();
    }

    public int getTotalDeCategorias(List<Pedido> listaDePedidos) {
        HashSet<String> categoriasProcessadas = new HashSet<>();

        listaDePedidos.stream()
                .map(Pedido::getCategoria)
                .forEach(c -> categoriasProcessadas.add(c));

        return categoriasProcessadas.size();
    }

}
