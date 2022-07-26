package br.com.alura.comex.main;

import br.com.alura.comex.dao.*;
import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.entity.StatusCategoria;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainProdutoDAO {

    public static void main(String[] args){


        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();

        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();
        List<Produto> listaProdutos = produtoDAO.listaIndisponível();
        em.getTransaction().commit();
        em.close();

        listaProdutos.stream().forEach(System.out::println);

    }

    private static void cadastrarProduto(){

        Categoria informatica = new Categoria("INFORMATICA", StatusCategoria.ATIVA);
        Produto impressora = new Produto("Impressora", "", new BigDecimal(2), 2, informatica);
        Produto mouse = new Produto("Mouse", "", new BigDecimal(2), 4, informatica);
        Produto teclado = new Produto("Teclado", "", new BigDecimal(2), 0, informatica);
        Produto tela = new Produto("Tela", "", new BigDecimal(2), 0, informatica);

        EntityManager em = JPAUtil.getEntityManager();

        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(informatica);
        produtoDAO.cadastrar(impressora);
        produtoDAO.cadastrar(mouse);
        produtoDAO.cadastrar(teclado);
        produtoDAO.cadastrar(tela);

        em.getTransaction().commit();
        em.close();

    }



}
