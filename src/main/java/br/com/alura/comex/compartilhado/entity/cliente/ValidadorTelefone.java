package br.com.alura.comex.compartilhado.entity.cliente;

public interface ValidadorTelefone {

    String validarDDD(String ddd);

    String validarNumero(String numero);
}
