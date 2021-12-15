package guru.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {

    @Test
    void testMultiplication() {
        // USD
        Money five = Money.dollar(5);

        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));

        // CHF
        Money fiveFranc = Money.franc(5);

        assertEquals(Money.franc(10), fiveFranc.times(2));
        assertEquals(Money.franc(15), fiveFranc.times(3));
    }

    @Test
    void testEquality() {
        // USD
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(10));

        // Side-by-side
        assertNotEquals(Money.dollar(5), Money.franc(5));

        // CHF
        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.franc(5), Money.franc(10));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    void testAddition() {
        Money fiveDollar = Money.dollar(5);
        Expression sum = fiveDollar.plus(fiveDollar);

        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");

        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnsSum() {
        Money fivedollar = Money.dollar(5);
        Expression result = fivedollar.plus(fivedollar);
        Sum sum = (Sum) result;

        assertEquals(fivedollar, sum.getAugmend());
        assertEquals(fivedollar, sum.getAddmend());
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");

        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");

        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");

        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
        assertEquals(1, new Bank().rate("CHF", "CHF"));
    }

    @Test
    void testMixedAddition() {
        Expression fiveUSD = Money.dollar(5);
        Expression tenCHF = Money.franc(10);
        Bank bank = new Bank();

        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveUSD.plus(tenCHF), "USD");

        assertEquals(Money.dollar(10), result);
    }

    @Test
    void testSumPlusMoney() {
        Expression fiveUSD = Money.dollar(5);
        Expression tenCHF = Money.franc(10);
        Bank bank = new Bank();

        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveUSD, tenCHF).plus(fiveUSD);
        Money result = bank.reduce(sum, "USD");

        assertEquals(Money.dollar(15), result);
    }

    @Test
    void testSumTimes() {
        Expression fiveUSD = Money.dollar(5);
        Expression tenCHF = Money.franc(10);
        Bank bank = new Bank();

        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveUSD, tenCHF).times(2);
        Money result = bank.reduce(sum, "USD");

        assertEquals(Money.dollar(20), result);
    }
}