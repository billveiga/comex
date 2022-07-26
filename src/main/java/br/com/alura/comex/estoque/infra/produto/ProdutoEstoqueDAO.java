package br.com.alura.comex.estoque.infra.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoEstoqueDAO extends JpaRepository<ProdutoEstoqueEntity, Long> {
}
