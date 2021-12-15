package guru.springframework;

import java.util.Objects;

/**
 * @Immutable
 */
public class Money implements Expression {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    // Factory method for dollar
    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    // Factory method for franc
    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    protected String currency() {
        return this.currency;
    }

    @Override
    public Money reduce(Bank bank, String toCurrency) {
        return new Money(this.amount / bank.rate(this.currency, toCurrency), toCurrency);
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Expression plus(Money addend) {
        return new Sum(this, addend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        Money money = (Money) o;
        return amount == money.amount && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
