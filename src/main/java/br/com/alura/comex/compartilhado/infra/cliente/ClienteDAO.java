package br.com.alura.comex.compartilhado.infra.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByCpf(String numeroCpf);

}
