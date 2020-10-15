package model;

import model.Expenses;
import model.Income;

// Represents a budget with a list of expenses, list of incomes, and a username
public class Budget {
    public Income income;
    public Expenses expenses;
    public String username;


    // EFFECTS: constructs a budget with an empty list of expenses, empty list
    // of incomes, and a username
    public Budget() {
        income = new Income();
        expenses = new Expenses();
    }

    public double getBalance() {
        return (income.getTotalIncome() - expenses.getTotalExpenses());
    }

    public Income getIncome() {
        return income;
    }

    public Expenses getExpenses() {
        return expenses;
    }

}
