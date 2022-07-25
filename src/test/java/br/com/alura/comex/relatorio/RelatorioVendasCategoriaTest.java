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


public class RelatorioVendasCategoriaTest {

    private GeradorRelatorio relatorio;
    private List<Pedido> listaPedidos;


    @BeforeEach
    public void setup() {
        relatorio = new RelatorioVendasCategoria();
    }

    @Test
    @DisplayName("Teste Relatório Vendas por Categoria - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relatorio.gerarConteudo(new ArrayList<>());
        });

    }

    @Test
    @DisplayName("Teste Relatório Vendas por Categoria - com um pedido ( uma categoria)")
    public void relatorioDeUmUnicoPedido() {

        listaPedidos = new ArrayList<>();
        listaPedidos.add(geraListaComUmPedido());
        assertEquals(relatorio.gerarConteudo(listaPedidos), this.conteudoEsperadoUmUnicoPedido());
    }

    @Test
    @DisplayName("Teste Relatório Vendas por Categoria - Pedidos com produtos de varias categorias")
    public void relatorioComMaisDeUmaCategoria() {
        assertEquals(relatorio.gerarConteudo(geraListaPedidosComMaisDeUmaCategorias()), conteudoEsperadoMaisDeUmaCategoria());
    }
    @Test
    @DisplayName("Teste Relatório Vendas por Categoria - cabeçalho")
    public void cabecalho() {
        assertEquals("\n#### RELATÓRIO DE VENDAS POR CATEGORIA", relatorio.gerarCabecalho());
    }

    private List<Pedido> geraListaPedidosComMaisDeUmaCategorias() {
        int year = 2022;
        int month = 2;
        int day = 19;

        List<Pedido> listaDePedidos = new ArrayList<>();

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(300)).
                quantidade(6).
                data(LocalDate.of(year, month, day)).
                cliente("Roger").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("AUTOMOTIVA").
                produto("entral multimidia").
                preco(new BigDecimal("711.18")).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Sara").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("Notebook Samsung").
                preco(new BigDecimal(1900)).
                quantidade(1).
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

    private String conteudoEsperadoMaisDeUmaCategoria() {
        return """
                \nCATEGORIA: AUTOMOTIVA
                QUANTIDADE VENDIDA: 1
                MONTANTE: %s
                \nCATEGORIA: INFORMATICA
                QUANTIDADE VENDIDA: 7
                MONTANTE: %s
                \nCATEGORIA: LIVROS
                QUANTIDADE VENDIDA: 2
                MONTANTE: %s
                """.formatted(FormatosImpressao.getRealFormat(new BigDecimal("711.18")),
                              FormatosImpressao.getRealFormat(new BigDecimal(1900)),
                              FormatosImpressao.getRealFormat(new BigDecimal("600.56")));
    }


    private String conteudoEsperadoUmUnicoPedido() {
        return """
                \nCATEGORIA: INFORMATICA
                QUANTIDADE VENDIDA: 1
                MONTANTE: %s
                """.formatted(FormatosImpressao.getRealFormat(new BigDecimal(300)));
    }

    private Pedido geraListaComUmPedido() {
        int year = 2022;
        int month = 2;
        int day = 17;

        return new PedidoBuilder().
                categoria("INFORMATICA").
                produto("NOTEBOOK").
                preco(new BigDecimal(3900)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Shaina").
                build();
    }

}
