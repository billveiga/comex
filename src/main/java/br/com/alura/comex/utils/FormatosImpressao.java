package br.com.alura.comex.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public final class FormatosImpressao {

    public static String getRealFormat(BigDecimal variavel) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(variavel.setScale(2, RoundingMode.HALF_DOWN));
    }
}
