package br.com.alura.comex.processador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ProcessadorDeXML extends Processador {

    private final String nomeArquivoXML = "pedidos.xml";

    @Override
    public ObjectMapper getMapper() {
        return new XmlMapper();
    }

    @Override
    public String getArquivo() {
        return nomeArquivoXML;
    }
}
