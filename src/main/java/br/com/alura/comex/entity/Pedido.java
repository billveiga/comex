package br.com.alura.comex.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data = LocalDate.now();

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDePedido> itens = new ArrayList<>();

    private BigDecimal desconto;

    @ManyToMany()
    private List<TipoDesconto> tipoDesconto = new ArrayList<>();

    public Pedido(Cliente cliente, BigDecimal desconto, List<TipoDesconto> tipoDesconto) {
        this.cliente = cliente;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public Pedido() {
        super();
    }

    public void adicionarItem(ItemDePedido item) {
        item.setPedido(this);
        this.itens.add(item);
    }

    public BigDecimal getValorTotalPedido() {
        return this.itens.stream().map(ItemDePedido::getValorTotalItem).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDePedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemDePedido> itens) {
        this.itens = itens;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public List<TipoDesconto> getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(List<TipoDesconto> tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }
}
