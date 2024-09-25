package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class AmortizacionPrestamo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el monto del préstamo (Principal P): ");
        BigDecimal principal = scanner.nextBigDecimal();

        System.out.print("Introduce la tasa de interés anual en porcentaje (por ejemplo, 5 para 5%): ");
        BigDecimal tasaAnual = scanner.nextBigDecimal().divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);

        System.out.print("Introduce la cantidad de años para el préstamo: ");
        int anios = scanner.nextInt();

        MathContext mc = new MathContext(10, RoundingMode.HALF_EVEN);

        BigDecimal tasaMensual = tasaAnual.divide(BigDecimal.valueOf(12), mc);

        int totalPagos = anios * 12;

        BigDecimal unoMasR = BigDecimal.ONE.add(tasaMensual, mc);
        BigDecimal potencia = unoMasR.pow(totalPagos, mc);
        BigDecimal numerador = principal.multiply(tasaMensual).multiply(potencia, mc);
        BigDecimal denominador = potencia.subtract(BigDecimal.ONE, mc);
        BigDecimal pagoMensual = numerador.divide(denominador, mc);

        System.out.println("El pago mensual será: " + pagoMensual.setScale(10, RoundingMode.HALF_EVEN));

        BigDecimal saldoPendiente = principal;

        System.out.println("\nEsquema de pagos:");
        System.out.println("Mes\tPago Mensual\tPago a Interés\tPago a Principal\tSaldo Pendiente");

        for (int mes = 1; mes <= totalPagos; mes++) {
            BigDecimal pagoIntereses = saldoPendiente.multiply(tasaMensual, mc);

            BigDecimal pagoPrincipal = pagoMensual.subtract(pagoIntereses, mc);

            saldoPendiente = saldoPendiente.subtract(pagoPrincipal, mc);

            System.out.println(mes + "\t" + pagoMensual.setScale(2, RoundingMode.HALF_EVEN) + "\t\t"
                    + pagoIntereses.setScale(2, RoundingMode.HALF_EVEN) + "\t\t"
                    + pagoPrincipal.setScale(2, RoundingMode.HALF_EVEN) + "\t\t\t"
                    + saldoPendiente.setScale(2, RoundingMode.HALF_EVEN));

            if (saldoPendiente.compareTo(BigDecimal.ZERO) < 0) {
                saldoPendiente = BigDecimal.ZERO;
            }
        }

        scanner.close();
    }
}

