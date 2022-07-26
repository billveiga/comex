package br.com.alura.comex.compartilhado.infra.ItemDePedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDePedidoDAO extends JpaRepository<ItemDePedidoEntity, Long> {
}
