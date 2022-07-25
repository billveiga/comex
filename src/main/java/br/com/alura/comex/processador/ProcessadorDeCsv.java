package br.com.alura.comex.processador;

import br.com.alura.comex.model.Pedido;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ProcessadorDeCsv {
    private final String caminhoArquivo = "pedidos.csv";

    public List<Pedido> getPedidos() throws FileNotFoundException {

        return new CsvToBeanBuilder<Pedido>(new FileReader(caminhoArquivo))
                .withType(Pedido.class)
                .withSeparator(',')
                .build()
                .parse();
    }

}
