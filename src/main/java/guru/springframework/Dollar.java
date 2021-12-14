package guru.springframework;

public class Dollar {

    private int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    void times(int multiplier) {
        this.amount = amount * multiplier;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
