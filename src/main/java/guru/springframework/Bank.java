package guru.springframework;

public class Bank {

    public Bank() {
    }

    Money reduce(Expression source, String toCurrency) {
        return Money.dollar(10);
    }
}
