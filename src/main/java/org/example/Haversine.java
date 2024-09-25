package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Haversine {

    private static final BigDecimal R = BigDecimal.valueOf(6371.0);
    private static final MathContext mc = new MathContext(34, RoundingMode.HALF_EVEN);

    private static BigDecimal toRadians(BigDecimal degrees) {
        BigDecimal pi = new BigDecimal(Math.PI, mc);
        return degrees.multiply(pi, mc).divide(BigDecimal.valueOf(180), mc);
    }

    public static BigDecimal calcularDistancia(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        BigDecimal lat1Rad = toRadians(lat1);
        BigDecimal lat2Rad = toRadians(lat2);
        BigDecimal lon1Rad = toRadians(lon1);
        BigDecimal lon2Rad = toRadians(lon2);

        BigDecimal deltaLat = lat2Rad.subtract(lat1Rad, mc);
        BigDecimal deltaLon = lon2Rad.subtract(lon1Rad, mc);

        BigDecimal sinDeltaLatDiv2 = BigDecimal.valueOf(Math.sin(deltaLat.divide(BigDecimal.valueOf(2), mc).doubleValue()));
        BigDecimal sinDeltaLonDiv2 = BigDecimal.valueOf(Math.sin(deltaLon.divide(BigDecimal.valueOf(2), mc).doubleValue()));

        BigDecimal a = sinDeltaLatDiv2.pow(2, mc)
                .add(BigDecimal.valueOf(Math.cos(lat1Rad.doubleValue()))
                        .multiply(BigDecimal.valueOf(Math.cos(lat2Rad.doubleValue())), mc)
                        .multiply(sinDeltaLonDiv2.pow(2, mc), mc), mc);

        BigDecimal c = BigDecimal.valueOf(2)
                .multiply(BigDecimal.valueOf(Math.atan2(Math.sqrt(a.doubleValue()), Math.sqrt(BigDecimal.ONE.subtract(a).doubleValue()))), mc);

        return R.multiply(c, mc).setScale(10, RoundingMode.HALF_EVEN);
    }

    public static void main(String[] args) {
        BigDecimal lat1 = new BigDecimal("40.416775");
        BigDecimal lon1 = new BigDecimal("-3.703790");
        BigDecimal lat2 = new BigDecimal("48.856613");
        BigDecimal lon2 = new BigDecimal("2.352222");

        BigDecimal distancia = calcularDistancia(lat1, lon1, lat2, lon2);
        System.out.println("La distancia entre Madrid y París es: " + distancia + " km");
    }
}
