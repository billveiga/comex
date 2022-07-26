package br.com.alura.comex.compartilhado.entity.cliente;


import lombok.*;

@Getter
@Setter @Builder
public class CPF {

    private final ValidadorCPF validadorCPF;
    private String numero;

    public CPF(ValidadorCPF validadorCPF, String numero) {
        this.validadorCPF = validadorCPF;
        this.numero = validadorCPF.validar(numero);
    }

}
