package br.com.alura.comex.dao;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Cliente;
import br.com.alura.comex.relatorio.RelatorioDeQtdPedidosCliente;

import javax.persistence.EntityManager;
import java.util.List;


public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        if (em == null ) throw new RuntimeException("EntityManager não pode ser nula");
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void atualiza(Cliente cliente) {
        this.em.merge(cliente);

    }

    public void remove(Cliente cliente) {
        cliente = em.merge(cliente);
        this.em.remove(cliente);
    }

    public Cliente buscaPorId(Long id) {
       return this.em.find(Cliente.class, id);
    }

    public List<Cliente> listaTodos() {
        String query = "SELECT c FROM Cliente c";
        return em.createQuery(query, Cliente.class).getResultList();
    }

    public List<Cliente> buscaPorNome(String nome){
        String query = "SELECT c FROM Cliente c WHERE c.nome = :nome";
        return em.createQuery(query, Cliente.class)
                .setParameter("nome", nome)
                .getResultList();
    }
    public List<Cliente> relatorioClientesMaisLucrativos(){
        String query =
                "SELECT cliente "
                        + "FROM Cliente cliente "
                        + "JOIN cliente.pedidos pedido "
                        + "GROUP BY pedido.cliente ";

        return em.createQuery(query, Cliente.class).getResultList();
    }


}
