package br.com.alura.comex.entity;

import javax.persistence.*;

@Entity
@Table(name = "tipo_desconto")
public class TipoDesconto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public TipoDesconto(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

//    FIDELIDADE, QUANTIDADE, PROMOCAO, NENHUM;
}
