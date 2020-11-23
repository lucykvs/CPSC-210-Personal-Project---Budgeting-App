package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a user with a username and associated budget
public class User implements Writable {
    public String name;
    public Transactions income;
    public Transactions expenses;
    public Transactions allTransactions;

    // EFFECTS: Constructs a user with given username and empty budget with transactions objects:
    //          income, expenses, and allTransactions
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

    // EFFECTS: returns all transactions in this user's budget
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
    // EFFECTS: adds a cost to user's expenses, and adds this cost to allTransactions
    public void addCost(Category category, String description, double amount) throws NegativeAmountException,
            NumberFormatException {
        if (amount < 0) {
            throw new NegativeAmountException();
        } else {
            expenses.addCost(category, description, amount);
            addToAllTransactions(new Cost(category, description, amount));
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a fund to user's income, and adds this fund to allTransactions
    public void addFund(Category category, String description, double amount) throws NegativeAmountException,
            NumberFormatException {
        if (amount < 0) {
            throw new NegativeAmountException();
        } else {
            income.addFund(category, description, amount);
            addToAllTransactions(new Fund(category, description, amount));
        }
    }

    // EFFECTS: adds transaction to allTransactions
    public void addToAllTransactions(Transaction transaction) {
        allTransactions.addTransaction(transaction);
    }

    // EFFECTS: if specified transaction is in expenses or income, removes transaction from expenses or income, and if
    //          specified transaction is in allTransactions, removes transaction and returns true; else returns false
    public boolean removeTransaction(Transaction transaction) throws NumberFormatException {
        expenses.removeTransaction(transaction);
        income.removeTransaction(transaction);
        return allTransactions.removeTransaction(transaction);
    }

    // EFFECTS: returns JSON representation of this user
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", name);
        json.put("transactions", transactionsToJson());
        return json;
    }

    // EFFECTS: returns transactions in this user's budget as a JSON array
    private JSONArray transactionsToJson() {
        JSONArray jsonArray;

        jsonArray = allTransactions.transactionsToJson();

        return jsonArray;
    }
}
