package br.com.alura.comex;


import br.com.alura.comex.menu.GerarMenu;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.processador.Processador;
import br.com.alura.comex.processador.ProcessadorDeJSON;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Processador arquivo = new ProcessadorDeJSON();
        List<Pedido> pedidos = arquivo.getPedidos();

        GerarMenu menu = new GerarMenu();
        menu.imprimeRelatorio(pedidos);

    }

}
