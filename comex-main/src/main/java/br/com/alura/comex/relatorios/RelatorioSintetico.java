package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

import br.com.alura.comex.*;

public class RelatorioSintetico {

	Object totalProdutosVendidos = 0;
	Object totalDePedidosRealizados = 0;
	BigDecimal montanteDeVendas = BigDecimal.ZERO;
	Pedido pedidoMaisBarato = null;
	Pedido pedidoMaisCaro = null;
	Object totalDeCategorias = 0;

	CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();

	public RelatorioSintetico(List<Pedido> listaPedidos) {
		super();
		if (listaPedidos == null || listaPedidos.isEmpty())
			throw new IllegalArgumentException("A lista de pedidos não pode ser vazia!");

		this.totalDePedidosRealizados = listaPedidos.size();
		Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();
		this.totalProdutosVendidos = listaPedidos.stream().mapToInt(pedido -> pedido.getQuantidade()).sum();
		listaPedidos.forEach(pedido -> categoriasProcessadas.add(pedido.getCategoria()));
		this.totalDeCategorias = getTotalCategorias(listaPedidos);
		this.montanteDeVendas = listaPedidos.stream().map(mapaPedidos).reduce(BigDecimal.ZERO, BigDecimal::add);
		this.pedidoMaisBarato = getPedidoMaisBarato(listaPedidos);
		this.pedidoMaisCaro = getPedidoMaisCaro(listaPedidos);

	}

	public Pedido getPedidoMaisCaro(List<Pedido> listaPedidos) {
		return listaPedidos.stream().max(Comparator.comparing(Pedido::getValorTotal))
				.orElseThrow(() -> new IllegalStateException("A lista de pedidos não pode ser vazia."));
	}

	public Pedido getPedidoMaisBarato(List<Pedido> listaPedidos) {
		return listaPedidos.stream().min(Comparator.comparing(Pedido::getValorTotal))
				.orElseThrow(() -> new IllegalStateException("A lista de pedidos não pode ser vazia."));
	}

	public BigDecimal getMontanteVendas(List<Pedido> listaPedidos) {
		Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();
		return montanteDeVendas = listaPedidos.stream().map(mapaPedidos).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public int getTotalCategorias(List<Pedido> listaPedidos) {
		listaPedidos.forEach(pedido -> categoriasProcessadas.add(pedido.getCategoria()));
		return categoriasProcessadas.size();
	}

	public Object getTotalProdutosVendidos(List<Pedido> listaPedidos) {
		return this.totalProdutosVendidos = listaPedidos.stream().mapToInt(pedido -> pedido.getQuantidade()).sum();
	}

	public int getTotalPedidosRealizados(List<Pedido> listaPedidos) {
		return listaPedidos.size();
	}

}
