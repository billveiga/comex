package br.com.alura.comex;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

import br.com.alura.comex.processadores.ProcessadorDeCsv;
import br.com.alura.comex.relatorios.RelatorioSintetico;

public class Main {

	public static void main(String[] args) {

		ProcessadorDeCsv processadorDeCsv = new ProcessadorDeCsv();
		String arquivoCSV = "pedidos.csv";
		List<Pedido> listaPedidos;
		listaPedidos = processadorDeCsv.listarPedidos(arquivoCSV);
		RelatorioSintetico relatorioSintetico = new RelatorioSintetico(listaPedidos);

		System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
		System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n",
				relatorioSintetico.getTotalPedidosRealizados(listaPedidos));
		System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n",
				relatorioSintetico.getTotalProdutosVendidos(listaPedidos));
		System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioSintetico.getTotalCategorias(listaPedidos));
		System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
				.format(relatorioSintetico.getMontanteVendas(listaPedidos).setScale(2, RoundingMode.HALF_DOWN)));
		System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n",
				NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(relatorioSintetico.getPedidoMaisBarato(listaPedidos).getValorTotal()),
				relatorioSintetico.getPedidoMaisBarato(listaPedidos).getProduto());
		System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n",
				NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(relatorioSintetico.getPedidoMaisCaro(listaPedidos).getValorTotal()),
				relatorioSintetico.getPedidoMaisCaro(listaPedidos).getProduto());
	}

}
