package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Endereco;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteForm {

    @NotNull
    @Size(min = 2)
    private String nome;

    @NotNull
    @NotEmpty
    private Long cpf;

    @NotNull
    @NotEmpty
    private String telefone;

    @NotNull
    @NotEmpty
    private String rua;

    @NotNull
    @NotEmpty
    private String numero;

    private String complemento;

    @NotNull
    @NotEmpty
    private String bairro;

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    @Size(max = 2)
    private String estado;

    public ClienteForm() {
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Cliente converter() {
        return new Cliente(nome, cpf, telefone, new Endereco(rua, numero, complemento, bairro, cidade, estado));
    }
}
