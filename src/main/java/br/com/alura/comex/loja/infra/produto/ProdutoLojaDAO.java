package br.com.alura.comex.loja.infra.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoLojaDAO extends JpaRepository<ProdutoLojaEntity, Long> {
}
