package br.com.alura.comex.processador;

import br.com.alura.comex.model.Pedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public abstract class Processador {


    public List<Pedido> getPedidos() throws URISyntaxException, IOException {

        URL recurso = ClassLoader.getSystemResource(this.getArquivo());
        FileReader reader = new FileReader(recurso.toURI().getPath());
        ObjectMapper objectMapper = this.getMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return getMapper().readValue(reader, new TypeReference<>() {
        });

    }

    public abstract ObjectMapper getMapper();

    public abstract String getArquivo();
}
