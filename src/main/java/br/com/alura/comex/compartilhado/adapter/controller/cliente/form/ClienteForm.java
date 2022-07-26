package br.com.alura.comex.compartilhado.adapter.controller.cliente.form;

import br.com.alura.comex.compartilhado.entity.cliente.CPF;
import br.com.alura.comex.compartilhado.entity.cliente.Cliente;
import br.com.alura.comex.compartilhado.entity.cliente.Endereco;
import br.com.alura.comex.compartilhado.entity.cliente.Telefone;
import br.com.alura.comex.compartilhado.infra.cliente.ClienteEntity;
import br.com.alura.comex.compartilhado.infra.cliente.EnderecoEntity;
import br.com.alura.comex.compartilhado.infra.cliente.validador.ValidadorProExpressaoRegular;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter @Setter
public class ClienteForm {

    @NotNull
    @Size(min = 2)
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    @NotEmpty
    @Size(min = 9, max = 9)
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

    public ClienteEntity converterParaClienteEntity() {
        return ClienteEntity.builder()
                .nome(this.nome)
                .cpf(this.cpf)
                .ddd(this.telefone.substring(0,2))
                .numeroTelefone(this.telefone.substring(2))
                .endereco(EnderecoEntity.builder()
                        .rua(this.rua)
                        .numero(this.numero)
                        .cidade(this.cidade)
                        .bairro(this.bairro)
                        .estado(this.estado)
                        .complemento(this.complemento)
                        .build())
                .build();
    }

    public Cliente converterParaCliente() {
        return Cliente.builder()
                .nome(this.nome)
                .cpf(CPF.builder()
                        .numero(this.numero)
                        .validadorCPF(new ValidadorProExpressaoRegular())
                        .build())
                .telefone(Telefone.builder()
                        .ddd(this.telefone.substring(0,2))
                        .numero(this.telefone.substring(2))
                        .build())
                .endereco(Endereco.builder()
                        .rua(this.rua)
                        .numero(this.numero)
                        .cidade(this.cidade)
                        .bairro(this.bairro)
                        .estado(this.estado)
                        .complemento(this.complemento)
                        .build())
                .build();
    }
}
