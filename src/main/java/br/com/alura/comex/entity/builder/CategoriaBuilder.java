package br.com.alura.comex.entity.builder;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.entity.StatusCategoria;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

public class CategoriaBuilder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private StatusCategoria status;

    private List<Produto> produtos;

    public CategoriaBuilder id() {
        return this;
    }

    public CategoriaBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public CategoriaBuilder status(StatusCategoria status) {
        this.status = status;
        return this;
    }

    public CategoriaBuilder produtos(List<Produto> produtos) {
        this.produtos = produtos;
        return this;
    }


    public Categoria build() {
        return new Categoria(nome, status);
    }
}
