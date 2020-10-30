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
}
