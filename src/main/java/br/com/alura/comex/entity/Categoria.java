package br.com.alura.comex.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Enumerated(EnumType.STRING)
    private StatusCategoria status;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos = new ArrayList<>();

    public Categoria(){

    }
    public Categoria(String nome, StatusCategoria status) {
        this.nome = nome;
        this.status = status;
    }

    public void adicionarProduto(Produto produto){
        produto.setCategoria(this);
        this.produtos.add(produto);

    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(StatusCategoria status) {
        this.status = status;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
