package br.com.alura.comex.dao;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualiza(Produto produto) {
        this.em.merge(produto);

    }

    public List<Produto> listaTodas() {
        String query = "SELECT p FROM Produto p";
        return em.createQuery(query, Produto.class).getResultList();
    }

    public List<Produto> listaIndisponível() {
        String query = "SELECT p FROM Produto p WHERE p.quantidade < 0";
        return em.createQuery(query, Produto.class).getResultList();
    }

    public Produto buscarPorId(Long id) {
        return this.em.find(Produto.class, id);
    }


}
