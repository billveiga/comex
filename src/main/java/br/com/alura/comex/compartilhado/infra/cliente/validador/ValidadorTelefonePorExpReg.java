package br.com.alura.comex.compartilhado.infra.cliente.validador;

import br.com.alura.comex.compartilhado.entity.cliente.ValidadorTelefone;

public class ValidadorTelefonePorExpReg implements ValidadorTelefone {

    @Override
    public String validarDDD(String ddd) {
        if (ddd == null ) throw new IllegalArgumentException("DDD é obrigatorio!");

        if (!ddd.matches("\\d{2}")) throw new IllegalArgumentException("DDD invalido!");

        return ddd;
    }

    @Override
    public String validarNumero(String numero) {
        if (numero == null ) throw new IllegalArgumentException("Numero do telefone é obrigatorio!");

        if (!numero.matches("\\d{8}|\\d{9}")) throw new IllegalArgumentException("Numero invalido!");

        return numero;
    }
}
