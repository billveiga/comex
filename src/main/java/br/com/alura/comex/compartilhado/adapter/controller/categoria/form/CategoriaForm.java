package br.com.alura.comex.compartilhado.adapter.controller.categoria.form;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Getter @Setter
public class CategoriaForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    public Categoria converterEmCategoria() {
        return Categoria.builder()
                .nome(this.nome)
                .status(StatusCategoria.ATIVA)
                .produtoEstoques(new ArrayList<>())
                .build();
    }
}
