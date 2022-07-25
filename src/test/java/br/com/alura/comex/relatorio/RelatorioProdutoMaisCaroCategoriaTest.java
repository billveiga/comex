package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import br.com.alura.comex.utils.FormatosImpressao;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelatorioProdutoMaisCaroCategoriaTest {

    private GeradorRelatorio relatorio;
    private List<Pedido> listaPedidos;

    @BeforeEach
    public void inicio() {
        listaPedidos = new ArrayList<>();
        relatorio = new RelatorioProdutoMaisCaroCategoria();
    }

    @Test
    @DisplayName("Teste Relatório Produto Mais Caro Por Categoria - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relatorio.gerarConteudo(new ArrayList<>());
        });

    }

    @Test
    @DisplayName("Teste Relatório Produto Mais Caro Por Categoria - com um pedido")
    public void relatorioDeUmUnicoPedido() {
        geraListaComUmPedido();
        Assertions.assertEquals(this.conteudoEsperadoUmUnicoPedido(), relatorio.gerarConteudo(listaPedidos));
    }

    @Test
    @DisplayName("Teste Relatório Produto Mais Caro Por Categoria - mais de um produto")
    public void relatorioDeMisDeUmProdutoCaro() {
        Assertions.assertEquals(this.conteudoEsperadoMultiplosPedido(), relatorio.gerarConteudo(geraListaPedidosComMultiplosProdutosCaros()));
    }


    @Test
    @DisplayName("Teste Relatório Produto Mais Caro Por Categoria - cabeçalho")
    public void cabecalhoRelatorioProdutoMaisCaroCategoria() {
        assertEquals("\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA", relatorio.gerarCabecalho());
    }

    private List<Pedido> geraListaPedidosComMultiplosProdutosCaros() {
        int year = 2022;
        int month = 4;
        int day = 29;

        List<Pedido> listaDePedidos = new ArrayList<>();

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("Impressora").
                preco(new BigDecimal(300)).
                quantidade(6).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("Notebook Samsung").
                preco(new BigDecimal(100)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("CAIO").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("AUTOMOTIVA").
                produto("Central multimidia").
                preco(new BigDecimal("711.18")).
                quantidade(6).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("LIVROS").
                produto("Building Microservices").
                preco(new BigDecimal("300.28")).
                quantidade(2).
                data(LocalDate.of(year, month, day)).
                cliente("CAIO").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("LIVROS").
                produto("Building Microservices").
                preco(new BigDecimal("300.28")).
                quantidade(2).
                data(LocalDate.of(year, month, day)).
                cliente("CAIO").
                build());

        return listaDePedidos;
    }

    private String conteudoEsperadoUmUnicoPedido() {
        return """
                \nCATEGORIA: INFORMATICA
                PRODUTO: IMPRESSORA
                PREÇO: %s
                """.formatted(FormatosImpressao.getRealFormat(new BigDecimal(300)));
    }

    private String conteudoEsperadoMultiplosPedido() {
        return """
                \nCATEGORIA: AUTOMOTIVA
                PRODUTO: Central multimidia
                PREÇO: %s
                \nCATEGORIA: INFORMATICA
                PRODUTO: Impressora
                PREÇO: %s
                \nCATEGORIA: LIVROS
                PRODUTO: Building Microservices
                PREÇO: %s
                """.formatted(FormatosImpressao.getRealFormat(new BigDecimal("711.18")),
                              FormatosImpressao.getRealFormat(new BigDecimal(300)),
                              FormatosImpressao.getRealFormat(new BigDecimal("300.28")));
    }

    private void geraListaComUmPedido() {
        int year = 2022;
        int month = 2;
        int day = 19;

        listaPedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(300)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Sara").
                build());
    }


}