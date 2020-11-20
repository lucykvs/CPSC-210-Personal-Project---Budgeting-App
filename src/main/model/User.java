package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a user with a username and a budget
public class User implements Writable {
    public String name;                   //username of account user
    public Transactions income;
    public Transactions expenses;
    public Transactions allTransactions;

    // EFFECTS: Constructs a user with given username and an empty budget
    public User(String username) {
        name = username;
        income = new Transactions();
        expenses = new Transactions();
        allTransactions = new Transactions();
    }

    // EFFECTS: returns name of user
    public String getName() {
        return name;
    }

    // EFFECTS: returns total income amount of user
    public double getTotalIncomeAmount() {
        return income.getTotalTransactions();
    }

    // EFFECTS: returns total expense amount of user
    public double getTotalExpenseAmount() {
        return expenses.getTotalTransactions();
    }

    // EFFECTS: returns the balance of budget's income and expenses. If balance is positive, income > expenses.
    //          If balance is negative, expenses > income.
    public double getBudgetBalance() {
        return (income.getTotalTransactions() - expenses.getTotalTransactions());
    }

    public Transactions getAllTransactions() {
        return allTransactions;
    }

    // EFFECTS: returns income object of this budget
    public Transactions getIncome() {
        return income;
    }

    // EFFECTS: returns expenses object of this budget
    public Transactions getExpenses() {
        return expenses;
    }

    // MODIFIES: this
    // EFFECTS: adds a cost to user's expenses
    public void addCost(Category category, String description, double amount) {
        expenses.addCost(category, description, amount);
        addToAllTransactions(new Cost(category, description, amount));
    }

    // MODIFIES: this
    // EFFECTS: adds a fund to user's income
    public void addFund(Category category, String description, double amount) {
        income.addFund(category, description, amount);
        addToAllTransactions(new Fund(category, description, amount));
    }

    public void addToAllTransactions(Transaction transaction) {
        allTransactions.addTransaction(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        allTransactions.removeTransaction(transaction);
        expenses.removeTransaction(transaction);
        income.removeTransaction(transaction);
    }

    // EFFECTS: returns JSON representation of user
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", name);
        json.put("transactions", transactionsToJson());
        return json;
    }

    // EFFECTS: returns expenses in this user's budget as a JSON array
    private JSONArray transactionsToJson() {
        JSONArray jsonArray;

        jsonArray = allTransactions.transactionsToJson();

        return jsonArray;
    }
}
