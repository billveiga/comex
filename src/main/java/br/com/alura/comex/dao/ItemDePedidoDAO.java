package br.com.alura.comex.dao;

import br.com.alura.comex.entity.ItemDePedido;

import javax.persistence.EntityManager;

public class ItemDePedidoDAO {
    private EntityManager em;

    public ItemDePedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(ItemDePedido item){
        this.em.persist(item);

    }
}
