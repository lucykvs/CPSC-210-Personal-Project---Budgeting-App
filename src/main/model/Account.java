package main.model;

// Represents an account having an owner name, id, list of expenses, list of costs, and budget balance
public class Account {
    private static int nextAccountId = 0;  //tracks id of next account created
    private String name;                   //username of account user
    private int id;                        //account id
    private double expenses;               //list of expenses
    private double income;                 //list of incomes
    private double budgetBal;              //balance of budget; income - expenses

    public Account(String userName, int id) {
        id = nextAccountId++;
        name = userName;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public double getExpenses() {
        return 0; //stub - return total value of expenses
    }

    public double getIncome() {
        return 0; //stub - return total value of income
    }

    public double getBalance() {
        return 0; //stub - return balance of income - expenses (+ve or -ve)
    }
}
