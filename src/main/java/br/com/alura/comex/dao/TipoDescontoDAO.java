package br.com.alura.comex.dao;

import br.com.alura.comex.entity.TipoDesconto;

import javax.persistence.EntityManager;

public class TipoDescontoDAO {

    private EntityManager em;

    public TipoDescontoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(TipoDesconto tipoDesconto){
        this.em.persist(tipoDesconto);
    }
}
