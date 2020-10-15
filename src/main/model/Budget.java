package model;

// Represents a budget with expenses and incomes
public class Budget {
    public Income income;
    public Expenses expenses;

    // EFFECTS: constructs a budget with empty expenses and empty income
    // of incomes
    public Budget() {
        income = new Income();
        expenses = new Expenses();
    }

    // EFFECTS: returns the balance of budget's income and expenses. If balance is positive, income > expenses.
    // If balance is negative, expenses > income.
    public double getBalance() {
        return (income.getTotalIncome() - expenses.getTotalExpenses());
    }

    // EFFECTS: returns income object of this budget
    public Income getIncome() {
        return income;
    }

    // EFFECTS: returns expenses object of this budget
    public Expenses getExpenses() {
        return expenses;
    }

}
