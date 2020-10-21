package Finance;

import java.util.ArrayList;

public class Finance {
    private static ArrayList<IncomeOfMoney> listIncome = new ArrayList<>();
    private static ArrayList<SpendingOfMoney> listTransactions = new ArrayList<>();

    public static ArrayList<IncomeOfMoney> getListIncome() {
        return listIncome;
    }

    public static ArrayList<SpendingOfMoney> getListTransactions() {
        return listTransactions;
    }

    // Метод добавления расхода
    public static void addTransaction(String nameTransaction, double amount){
        SpendingOfMoney spending = new SpendingOfMoney(nameTransaction,amount);
        listTransactions.add(spending);
    }

    // Метод добавления источника дохода
    public static void addIncome(String nameIncome, double amount){
        IncomeOfMoney income = new IncomeOfMoney(nameIncome,amount);
        listIncome.add(income);
    }

    // Метод выводит все доходы на экран
    public static void printAllIncome(){
        listIncome.forEach(incomeOfMoney -> System.out.println(incomeOfMoney.getIncomeOfMoney() + ": " + incomeOfMoney.getAmount()));
    }

    // Метод выводит все расходы на экран
    public static void printAllTransactions(){
        listTransactions.forEach(spendingOfMoney ->
                System.out.println(spendingOfMoney.getSpendingOfMoney() + ": "
                        + spendingOfMoney.getAmount()));
    }

    // Метод находящий сумму все расходов
    public static double totalTransactions(){
        double total = 0;
        for (SpendingOfMoney spending : listTransactions){
           total += spending.getAmount();

        }
        return total;
    }

    // Метод находящий суммы всех доходов
    public static double totalIncome(){
        double total = 0;
        for (IncomeOfMoney income : listIncome){
            total += income.getAmount();
        }
        return total;
    }

    public static Double result(double income, double spending){
        return income - spending;
    }
}
