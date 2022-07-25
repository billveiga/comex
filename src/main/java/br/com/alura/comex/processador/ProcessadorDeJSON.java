package br.com.alura.comex.processador;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessadorDeJSON extends Processador{

    private final String nomeArquivoJson = "pedidos.json";
    @Override
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Override
    public String getArquivo() {
        return nomeArquivoJson;
    }
}
