package br.com.alura.comex.compartilhado.entity.cliente;


import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import br.com.alura.comex.compartilhado.entity.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
public class Cliente {

    private Long id;

    private String nome;

    private CPF cpf;

    private Telefone telefone;

    private Endereco endereco;

    private List<Pedido> pedidos = new ArrayList<>();

    private Usuario usuario;

    public void adicionarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }

    public BigDecimal getMontanteGasto(){
        return pedidos.stream().map(Pedido::getValorTotalPedido).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int quantidadePedidos(){
        return pedidos.size();
    }


}
