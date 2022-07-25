package br.com.alura.comex.menu;

import br.com.alura.comex.relatorio.GeradorRelatorio;

public class OpcoesEscolhaProxy extends OpcoesEscolha {

    private GeradorRelatorio tipoRelatorioEscolhido;

    private int numeroRelatorio;
    private OpcoesEscolha opcoesEscolha;

    public OpcoesEscolhaProxy(OpcoesEscolha opcoesEscolha) {
        this.opcoesEscolha = opcoesEscolha;
    }

    @Override
    public GeradorRelatorio verificaExistenciaRelatorio(int tipoRelatorioEscolhido) {
        if (this.numeroRelatorio != tipoRelatorioEscolhido) {
            this.tipoRelatorioEscolhido = opcoesEscolha.verificaExistenciaRelatorio(tipoRelatorioEscolhido);
            this.numeroRelatorio = tipoRelatorioEscolhido;
        }
        return this.tipoRelatorioEscolhido;
    }

}
