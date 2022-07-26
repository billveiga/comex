package br.com.alura.comex.relatorio;

public class RelatorioDeQtdPedidosCliente {
    private String nomeCliente;
    private Long quantidadePedido;

    public RelatorioDeQtdPedidosCliente(String nomeCliente, Long quantidadePedido) {
        this.nomeCliente = nomeCliente;
        this.quantidadePedido = quantidadePedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Long getQuantidadePedido() {
        return quantidadePedido;
    }

    @Override
    public String toString() {
        return "RelatorioDeQtdPedidosCliente{" +
                "nomeCliente='" + nomeCliente + '\'' +
                ", quantidadePedido=" + quantidadePedido +
                '}';
    }
}
