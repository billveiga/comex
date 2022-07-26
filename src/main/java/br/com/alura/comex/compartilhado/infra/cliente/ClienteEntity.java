package br.com.alura.comex.compartilhado.infra.cliente;


import br.com.alura.comex.compartilhado.entity.cliente.CPF;
import br.com.alura.comex.compartilhado.entity.cliente.Cliente;
import br.com.alura.comex.compartilhado.entity.cliente.Endereco;
import br.com.alura.comex.compartilhado.entity.cliente.Telefone;
import br.com.alura.comex.compartilhado.entity.usuario.Usuario;
import br.com.alura.comex.compartilhado.infra.cliente.validador.ValidadorProExpressaoRegular;
import br.com.alura.comex.compartilhado.infra.pedido.PedidoEntity;
import br.com.alura.comex.compartilhado.infra.usuario.UsuarioEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @Builder
@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String ddd;

    @Column(nullable = false)
    private String numeroTelefone;

    @Embedded
    private EnderecoEntity endereco;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<PedidoEntity> pedidos = new ArrayList<>();

//    @OneToOne(optional = false)
//    private UsuarioEntity usuario;


    public static ClienteEntity converter(Cliente cliente) {
        return ClienteEntity.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf().getNumero())
                .endereco(EnderecoEntity.builder()
                        .cidade(cliente.getEndereco().getCidade())
                        .bairro(cliente.getEndereco().getBairro())
                        .estado(cliente.getEndereco().getEstado())
                        .rua(cliente.getEndereco().getRua())
                        .numero(cliente.getEndereco().getNumero())
                        .complemento(cliente.getEndereco().getComplemento())
                        .build())
                .ddd(cliente.getTelefone().getDdd())
                .numeroTelefone(cliente.getTelefone().getNumero())
                .pedidos(new ArrayList<>())
                .build();
    }

    public void adicionarPedido(PedidoEntity pedidoEntity){
        pedidoEntity.setClienteEntity(this);
        this.pedidos.add(pedidoEntity);
    }

    public BigDecimal getMontanteGasto(){
        return pedidos.stream().map(PedidoEntity::getValorTotalPedido).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Cliente paraCliente() {
        return Cliente.builder()
                .id(this.id)
                .cpf(CPF.builder().numero(this.numeroTelefone).validadorCPF(new ValidadorProExpressaoRegular()).build())
                .nome(this.nome)
                .telefone(Telefone.builder()
                        .ddd(this.ddd)
                        .numero(this.numeroTelefone)
                        .build())
                .endereco(Endereco.builder()
                        .rua(this.endereco.getRua())
                        .numero(this.endereco.getNumero())
                        .cidade(this.endereco.getCidade())
                        .bairro(this.endereco.getBairro())
                        .estado(this.endereco.getEstado())
                        .complemento(this.endereco.getComplemento())
                        .build())
                .pedidos(new ArrayList<>())
                .usuario(new Usuario())
                .build();
    }

}
