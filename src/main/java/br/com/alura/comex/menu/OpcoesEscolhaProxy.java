package br.com.alura.comex.menu;

import br.com.alura.comex.relatorio.GeradorRelatorio;

public class OpcoesEscolhaProxy extends OpcoesEscolha{

    private GeradorRelatorio tipoRelatorioEscolhido;
    private OpcoesEscolha opcoesEscolha;

    public OpcoesEscolhaProxy( OpcoesEscolha opcoesEscolha) {
        this.opcoesEscolha = opcoesEscolha;
    }

    @Override
    public GeradorRelatorio verificaExistenciaRelatorio(int tipoRelatorioEscolhido) {
        if (this.tipoRelatorioEscolhido == null){
            this.tipoRelatorioEscolhido = opcoesEscolha.verificaExistenciaRelatorio(tipoRelatorioEscolhido);
        }
        return this.tipoRelatorioEscolhido;
    }
}
