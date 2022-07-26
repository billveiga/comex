package br.com.alura.comex.compartilhado.infra.cliente.validador;

import br.com.alura.comex.compartilhado.entity.cliente.ValidadorCPF;

public class ValidadorProExpressaoRegular implements ValidadorCPF {

    @Override
    public String validar(String cpf) {
        return cpf;
    }
}
