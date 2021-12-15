package guru.springframework;

public class Sum implements Expression {

    Expression augmend;
    Expression addmend;

    public Sum(Expression augmend, Expression addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    @Override
    public Money reduce(Bank bank, String toCurrency) {
        // Reduce 2 money objects to 1 money object
        int amount = this.augmend.reduce(bank, toCurrency).getAmount() + this.addmend.reduce(bank, toCurrency).getAmount();

        return new Money(amount, toCurrency);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(this.augmend.times(multiplier), this.addmend.times(multiplier));
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, this.addmend);
    }

    public Expression getAugmend() {
        return augmend;
    }

    public void setAugmend(Money augmend) {
        this.augmend = augmend;
    }

    public Expression getAddmend() {
        return addmend;
    }

    public void setAddmend(Money addmend) {
        this.addmend = addmend;
    }
}
