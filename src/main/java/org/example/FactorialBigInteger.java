package org.example;

import java.math.BigInteger;
import java.util.Scanner;

public class FactorialBigInteger {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce un número para calcular el factorial: ");
        int numero = scanner.nextInt();

        BigInteger factorial = calcularFactorial(numero);

        System.out.println("El factorial de " + numero + " es: " + factorial);
    }

    public static BigInteger calcularFactorial(int numero) {
        BigInteger resultado = BigInteger.ONE;

        for (int i = 1; i <= numero; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }

        return resultado;
    }
}
