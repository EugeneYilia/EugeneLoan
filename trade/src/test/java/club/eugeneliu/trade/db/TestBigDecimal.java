package club.eugeneliu.trade.db;

import java.math.BigDecimal;

public class TestBigDecimal {
    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal("1.2");
        BigDecimal bigDecimal2 = new BigDecimal("2.3");
        bigDecimal1 = bigDecimal1.add(bigDecimal2);

        System.out.println(bigDecimal1.doubleValue());
    }
}
