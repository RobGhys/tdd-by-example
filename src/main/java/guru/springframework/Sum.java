package guru.springframework;

public class Sum implements Expression {

    private Money augmend;
    private Money addmend;

    public Sum(Money augmend, Money addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    @Override
    public Money reduce(Bank bank, String toCurrency) {
        // Reduce 2 money objects to 1 money object
        int amount = this.augmend.getAmount() + this.addmend.getAmount();

        return new Money(amount, toCurrency);
    }

    public Money getAugmend() {
        return augmend;
    }

    public void setAugmend(Money augmend) {
        this.augmend = augmend;
    }

    public Money getAddmend() {
        return addmend;
    }

    public void setAddmend(Money addmend) {
        this.addmend = addmend;
    }
}
