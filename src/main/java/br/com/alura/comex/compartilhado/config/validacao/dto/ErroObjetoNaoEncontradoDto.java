package br.com.alura.comex.compartilhado.config.validacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@Builder
public class ErroObjetoNaoEncontradoDto {

    private String status;

    private String classe;

    private String mensagem;
}
