package Finance;

public class SpendingOfMoney {
    private String spendingOfMoney;
    private double amount;

    public SpendingOfMoney(String spendingOfMoney, double amount){
        this.spendingOfMoney = spendingOfMoney;
        this.amount = amount;
    }

    public String getSpendingOfMoney() {
        return spendingOfMoney;
    }

    public double getAmount() {
        return amount;
    }
}
