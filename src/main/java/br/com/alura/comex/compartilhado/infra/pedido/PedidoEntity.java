package br.com.alura.comex.compartilhado.infra.pedido;


import br.com.alura.comex.compartilhado.entity.enuns.TipoDesconto;
import br.com.alura.comex.compartilhado.entity.itemDePedido.ItemDePedido;
import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import br.com.alura.comex.compartilhado.infra.ItemDePedido.ItemDePedidoEntity;
import br.com.alura.comex.compartilhado.infra.cliente.ClienteEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data = LocalDate.now();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Valid
    private ClienteEntity clienteEntity;

    @OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL)
    @Valid
    private List<ItemDePedidoEntity> itens = new ArrayList<>();

    private BigDecimal desconto = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto = TipoDesconto.NENHUM;


    public PedidoEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public static PedidoEntity converter(Pedido pedido) {
        PedidoEntity pedidoEntity = PedidoEntity.builder()
                .id(pedido.getId())
                .data(pedido.getData())
                .desconto(pedido.getDesconto())
                .clienteEntity(ClienteEntity.converter(pedido.getCliente()))
                .itens(new ArrayList<>())
                .tipoDesconto(pedido.getTipoDesconto())
                .build();

        List<ItemDePedidoEntity> lista = pedido.getItens().stream().map(ItemDePedidoEntity::converter).toList();
        lista.forEach(pedidoEntity::adicionarItem);

        return pedidoEntity;
    }


    public void adicionarItem(ItemDePedidoEntity item) {
        item.setPedidoEntity(this);
        this.itens.add(item);
    }

    public int getQuantidadeDeProdutos() {
        return this.itens.stream()
                .mapToInt(ItemDePedidoEntity::getQuantidade)
                .sum();
    }

    public BigDecimal getValorTotalPedido() {
        return this.itens.stream()
                .map(ItemDePedidoEntity::getValorTotalItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(this.getValorTotalDescontos());
    }

    public BigDecimal getValorTotalDescontos() {
        return this.itens.stream()
                .map(ItemDePedidoEntity::getDesconto)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(this.desconto);
    }

    public Pedido paraPedido() {
        Pedido pedido = Pedido.builder()
                .id(this.id)
                .data(this.data)
                .desconto(this.desconto)
                .cliente(this.clienteEntity.paraCliente())
                .itens(new ArrayList<>())
                .tipoDesconto(this.tipoDesconto)
                .build();

        List<ItemDePedido> lista = this.itens.stream().map(ItemDePedidoEntity::paraItemDePedido).toList();
        lista.forEach(pedido::adicionarItem);

        return pedido;
    }

}
