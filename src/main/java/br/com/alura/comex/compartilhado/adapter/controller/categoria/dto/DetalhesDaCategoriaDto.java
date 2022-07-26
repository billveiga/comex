package br.com.alura.comex.compartilhado.adapter.controller.categoria.dto;

import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import br.com.alura.comex.estoque.infra.produto.ProdutoEstoqueEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDaCategoriaDto {

    private String nome;
    private StatusCategoria status;

    private List<String> produtos;

    public DetalhesDaCategoriaDto(CategoriaEntity categoria) {
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtos = categoria.getProdutoEntities().stream().map(ProdutoEstoqueEntity::getNome).collect(Collectors.toList());
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
