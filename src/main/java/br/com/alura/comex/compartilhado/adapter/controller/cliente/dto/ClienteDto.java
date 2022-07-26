package br.com.alura.comex.compartilhado.adapter.controller.cliente.dto;

import br.com.alura.comex.compartilhado.entity.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClienteDto {

    private String nome;

    private String cpf;

    private String telefone;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf().getNumero();
        this.telefone = cliente.getTelefone().telefoneCompleto();
        this.rua = cliente.getEndereco().getRua();
        this.numero = cliente.getEndereco().getNumero();
        this.complemento = cliente.getEndereco().getComplemento();
        this.bairro = cliente.getEndereco().getBairro();
        this.cidade = cliente.getEndereco().getCidade();
        this.estado = cliente.getEndereco().getEstado();
    }
}
