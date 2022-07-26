package br.com.alura.comex.entity.builder;

import br.com.alura.comex.entity.Cliente;
import br.com.alura.comex.entity.Endereco;
import br.com.alura.comex.entity.Pedido;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

public class ClienteBuilder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private Long cpf;

    private Long telefone;

    private Endereco endereco;

    private List<Pedido> listaDePedido;

    public ClienteBuilder id(){
        return this;
    }

    public ClienteBuilder nome(String nome){
        this.nome = nome ;
        return this;
    }

    public ClienteBuilder cpf(Long cpf){
        this.cpf = cpf;
        return this;
    }

    public ClienteBuilder telefone(Long telefone){
        this.telefone = telefone;
        return this;
    }

    public ClienteBuilder endereco(Endereco endereco){
        this.endereco = endereco;
        return this;
    }

    public ClienteBuilder listaDePedido(List<Pedido> lista){
        this.listaDePedido = lista;
        return this;
    }

    public Cliente build(){
        return new Cliente(nome, cpf, telefone, endereco);
    }

}
