package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.relatorio.indicadores.Indicador;

import java.util.List;

public class RelaotrioSinteticoRefactor implements GeradorRelatorio {

    private static final List<Indicador> INDICADORS = List.of(

    );


    @Override
    public String gerarCabecalho() {
        return null;
    }

    @Override
    public String gerarConteudo(List<Pedido> listaDePedidos) {
        return null;
    }
}
