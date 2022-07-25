package br.com.alura.comex.model;

import br.com.alura.comex.model.builder.PedidoBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PedidoBuilderTest {
    private static final Logger LOG = LogManager.getLogger(PedidoBuilderTest.class);

    @Test
    @DisplayName("Test Data with builder in the plain class")
    public void plainClassWithBuilder() {
        int year = 2022;
        int month = 2;
        int day = 19;

        Pedido pedido = new PedidoBuilder().
                categoria("INFORMATICA").
                produto("COMPUTADOR").
                preco(new BigDecimal(1900)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Roger").
                build();

        LOG.info(pedido);

        assertNotNull(pedido);
    }

}