package br.com.alura.comex.compartilhado.infra.produto;

import br.com.alura.comex.compartilhado.entity.produto.Dimensao;
import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DimensaoEntity {

    private int comprimento;

    private int altura;

    private int largura;

    private BigDecimal peso;

    public static DimensaoEntity converter(Dimensao dimensao) {
        return DimensaoEntity.builder()
                .comprimento(dimensao.getComprimento())
                .altura(dimensao.getAltura())
                .largura(dimensao.getLargura())
                .peso(dimensao.getPeso())
                .build();
    }

    public Dimensao paraDimensao() {
        return Dimensao.builder()
                .comprimento(this.comprimento)
                .altura(this.altura)
                .largura(this.largura)
                .peso(this.peso)
                .build();
    }
}
