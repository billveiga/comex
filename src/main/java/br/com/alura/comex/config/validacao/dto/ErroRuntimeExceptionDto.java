package br.com.alura.comex.config.validacao.dto;

public class ErroRuntimeExceptionDto {

    private String mensagem;
    private Class<? extends RuntimeException> classe;

    public ErroRuntimeExceptionDto(Class<? extends RuntimeException> aClass, String message) {
        this.classe = aClass;
        this.mensagem = message;

    }

    public String getMensagem() {
        return mensagem;
    }

    public Class<? extends RuntimeException> getClasse() {
        return classe;
    }
}
