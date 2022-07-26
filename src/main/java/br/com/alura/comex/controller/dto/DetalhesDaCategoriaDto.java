package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.enuns.StatusCategoria;

import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDaCategoriaDto {

    private String nome;
    private StatusCategoria status;

    private List<String> produtos;

    public DetalhesDaCategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtos = categoria.getProdutos().stream().map(Produto::getName).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return "DetalhesDaCategoriaDto{" +
                "nome='" + nome + '\'' +
                ", status=" + status +
                ", produtos=" + produtos +
                '}';
    }
}
