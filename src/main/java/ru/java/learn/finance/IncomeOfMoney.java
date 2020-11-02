package ru.java.learn.finance;

public class IncomeOfMoney {
    private String incomeOfMoney;
    private double amount;
    public IncomeOfMoney(String incomeOfMoney, double amount){
        this.incomeOfMoney = incomeOfMoney;
        this.amount = amount;
    }

    public String getIncomeOfMoney() {
        return incomeOfMoney;
    }

    public void setIncomeOfMoney(String incomeOfMoney) {
        this.incomeOfMoney = incomeOfMoney;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

