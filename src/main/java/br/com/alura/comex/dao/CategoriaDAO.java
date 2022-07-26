package br.com.alura.comex.dao;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.StatusCategoria;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CategoriaDAO {

    private EntityManager em;

    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void atualiza(Categoria categoria) {
        this.em.merge(categoria);

    }

    public List<Categoria> listaTodas() {
        String query = "SELECT c FROM Categoria c";
        return em.createQuery(query, Categoria.class).getResultList();
    }

    public List<Categoria> listaInativas() {
        Query query = em.createQuery("FROM" + Categoria.class.getName() + "As a WHERE a.status = :param");
        query.setParameter("parm", StatusCategoria.INATIVA);
        return query.getResultList();
    }

    public Categoria buscarPorId(Long id) {
        return this.em.find(Categoria.class, id);
    }
}
