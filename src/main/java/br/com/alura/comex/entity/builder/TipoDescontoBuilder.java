package br.com.alura.comex.entity.builder;

import br.com.alura.comex.entity.TipoDesconto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class TipoDescontoBuilder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public TipoDescontoBuilder id(){
        return this;
    }

    public TipoDescontoBuilder nome(String nome){
        this.nome = nome;
        return this;
    }

    public TipoDesconto builder(){
        return new TipoDesconto(nome);
    }
}
