package br.com.alura.comex.compartilhado.adapter.controller.categoria.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter @ToString
public class AtualizacaoCategoriaForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

}
