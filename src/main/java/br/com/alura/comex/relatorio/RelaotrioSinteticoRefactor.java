package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.relatorio.indicadores.Indicador;

import java.util.List;

public class RelaotrioSinteticoRefactor extends GeradorRelatorio {

    private static final List<Indicador> INDICADORS = List.of(

    );


    @Override
    protected String gerarCabecalho() {
        return null;
    }

    @Override
    protected String gerarConteudo(List<Pedido> listaDePedidos) {
        return null;
    }
}
