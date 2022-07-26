package br.com.alura.comex.compartilhado.entity.cliente;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {

    @Autowired
    private ValidadorTelefone validadorTelefone;
    private String ddd;
    private String numero;

    public Telefone(String ddd, String numero) {
        this.ddd = validadorTelefone.validarDDD(ddd);
        this.numero = validadorTelefone.validarNumero(numero);
    }

    public  String telefoneCompleto(){
        return ddd.concat(numero);
    }
}
