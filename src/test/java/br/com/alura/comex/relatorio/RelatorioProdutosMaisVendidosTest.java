package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelatorioProdutosMaisVendidosTest {

    private GeradorRelatorio relatorio;
    private List<Pedido> listaPedidos;

    @BeforeEach
    public void inicio() {
        relatorio = new RelatorioProdutosMaisVendidos();
        listaPedidos = new ArrayList<>();
    }

    @Test
    @DisplayName("Teste Relatório Produtos Mais Vendidos - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relatorio.gerarConteudo(new ArrayList<>());
        });

    }

    @Test
    @DisplayName("Teste Relatório Produtos Mais Vendidos - lista com um pedido")
    public void relatorioDeUmUnicoPedido() {

        listaPedidos.add(geraListaComUmPedido());

        assertEquals(relatorio.gerarConteudo(listaPedidos), this.conteudoEsperadoUmUnicoPedido());
    }

    @Test
    @DisplayName("Teste Relatório Produtos Mais Vendidos - lista com produtos com a mesma quantidade de vendas")
    public void relatorioComProdutosDeMesmaQuantidade() {
        assertEquals(relatorio.gerarConteudo(geraListaPedidosComMaisDeUmaCategoriaEQuantidadesIguais()),
                this.conteudoEsperadoParaQuantidadesIguaisDeProduto());
    }

    @Test
    @DisplayName("Teste Cabeçalho")
    public void cabecalhoRelatorioProdutosMaisVendidos(){
        assertEquals("\n#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS", relatorio.gerarCabecalho());

    }

    private List<Pedido> geraListaPedidosComMaisDeUmaCategoriaEQuantidadesIguais() {
        int year = 2022;
        int month = 2;
        int day = 17;

        List<Pedido> listaDePedidos = new ArrayList<>();

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("NOTEBOOK DELL").
                preco(new BigDecimal(3900)).
                quantidade(2).
                data(LocalDate.of(year, month, day)).
                cliente("Shaina").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("NOTEBOOK DELL").
                preco(new BigDecimal(3900)).
                quantidade(2).
                data(LocalDate.of(year, month, day)).
                cliente("Sara").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("GAMES").
                produto("GTA V - XBOX").
                preco(new BigDecimal("199")).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Bill").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("GAMES").
                produto("GTA V - XBOX").
                preco(new BigDecimal("199")).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Roger").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("LIVROS").
                produto("USE A CABEÇA - JAVA").
                preco(new BigDecimal("240")).
                quantidade(2).
                data(LocalDate.of(year, month, day)).
                cliente("Sara").
                build());

        return listaDePedidos;
    }

    private String conteudoEsperadoParaQuantidadesIguaisDeProduto(){
        return """
                \nPRODUTO: GTA V - XBOX
                QUANTIDADE: 2
                \nPRODUTO: NOTEBOOK DELL
                QUANTIDADE: 4
                \nPRODUTO: USE A CABEÇA - JAVA
                QUANTIDADE: 2
                """;

    }

    private String conteudoEsperadoUmUnicoPedido() {
        return """
                \nPRODUTO: GTA V - XBOX
                QUANTIDADE: 1
                """;
    }

    private Pedido geraListaComUmPedido() {
        int year = 2022;
        int month = 2;
        int day = 17;

        return new PedidoBuilder().
                categoria("GAMES").
                produto("GTA V - XBOX").
                preco(new BigDecimal(199)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Haroldo").
                build();
    }

}