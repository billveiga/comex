package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;

import java.util.List;

public abstract class GeradorRelatorio {

    public final void gerarRelatorio(List<Pedido> listaDePedidos){
        String cabecalho = this.gerarCabecalho();
        String conteudo = this.gerarConteudo(listaDePedidos);
        this.gerarVisualizacao(cabecalho, conteudo);

    }
    protected abstract String gerarCabecalho();
    protected abstract String gerarConteudo(List<Pedido> listaDePedidos);
    protected void gerarVisualizacao(String cabecalho, String conteudo){
        System.out.println(cabecalho);
        System.out.println(conteudo);
    };

}
