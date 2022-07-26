package br.com.alura.comex.config.validacao;

import br.com.alura.comex.config.validacao.dto.ErroDeFormularioDto;
import br.com.alura.comex.config.validacao.dto.ErroRuntimeExceptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeFormularioDto> listaErro = new ArrayList<>();
        List<FieldError> camposErro = exception.getBindingResult().getFieldErrors();

        camposErro.forEach( error -> {
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            ErroDeFormularioDto erro = new ErroDeFormularioDto(error.getField(), mensagem);
            listaErro.add(erro);
        });

        return listaErro;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErroRuntimeExceptionDto handle(RuntimeException exception){;
            return new ErroRuntimeExceptionDto(exception.getClass(), exception.getMessage());
    }


}
