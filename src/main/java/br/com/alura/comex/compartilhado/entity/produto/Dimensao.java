package br.com.alura.comex.compartilhado.entity.produto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dimensao {

    private int comprimento;

    private int altura;

    private int largura;

    private BigDecimal peso;
}
