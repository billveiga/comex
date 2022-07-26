package br.com.alura.comex.relatorio;

public class RelatorioDeQtdProdutosVendidosCategoria {
    private String nomeCategoria;
    private Long qtdDeProdutosVendidos;

    public RelatorioDeQtdProdutosVendidosCategoria(String nomeCategoria, Long qtdDeProdutosVendidos) {
        this.nomeCategoria = nomeCategoria;
        this.qtdDeProdutosVendidos = qtdDeProdutosVendidos;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Long getQtdDeProdutosVendidos() {
        return qtdDeProdutosVendidos;
    }

    @Override
    public String toString() {
        return "RelatorioDeQtdProdutosVendidosCategoria{" +
                "nomeCategoria='" + nomeCategoria + '\'' +
                ", qtdDeProdutosVendidos=" + qtdDeProdutosVendidos +
                '}';
    }
}
