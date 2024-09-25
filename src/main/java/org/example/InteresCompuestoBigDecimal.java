package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class InteresCompuestoBigDecimal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el monto principal (P): ");
        BigDecimal principal = scanner.nextBigDecimal();

        System.out.print("Introduce la tasa de interés anual en porcentaje (por ejemplo, 5 para 5%): ");
        BigDecimal tasaAnual = scanner.nextBigDecimal().divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);

        System.out.print("Introduce el número de años (n): ");
        int anios = scanner.nextInt();

        MathContext mc = new MathContext(10, RoundingMode.HALF_EVEN);

        BigDecimal unoMasR = BigDecimal.ONE.add(tasaAnual, mc);
        BigDecimal cantidadFinal = principal.multiply(unoMasR.pow(anios, mc), mc);

        System.out.println("El monto total en la cuenta después de " + anios + " años es: " + cantidadFinal.setScale(10, RoundingMode.HALF_EVEN));
    }
}

