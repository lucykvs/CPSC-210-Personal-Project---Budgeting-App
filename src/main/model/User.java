package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a user with a username and a budget
public class User implements Writable {
    public String name;                   //username of account user
    public Income income;
    public Expenses expenses;
    public Transactions transactions;

    // EFFECTS: Constructs a user with given username and an empty budget
    public User(String username) {
        name = username;
        income = new Income();
        expenses = new Expenses();
        transactions = new Transactions();
    }

    // EFFECTS: returns name of user
    public String getName() {
        return name;
    }

    // EFFECTS: returns total income amount of user
    public double getTotalIncomeAmount() {
        return income.getTotalIncome();
    }

    // EFFECTS: returns total expense amount of user
    public double getTotalExpenseAmount() {
        return expenses.getTotalExpenses();
    }

    // EFFECTS: returns the balance of budget's income and expenses. If balance is positive, income > expenses.
    //          If balance is negative, expenses > income.
    public double getBudgetBalance() {
        return (income.getTotalIncome() - expenses.getTotalExpenses());
    }

    public Transactions getTransactions() {
        return transactions;
    }

    // EFFECTS: returns income object of this budget
    public Income getIncome() {
        return income;
    }

    // EFFECTS: returns expenses object of this budget
    public Expenses getExpenses() {
        return expenses;
    }

    // MODIFIES: this
    // EFFECTS: adds a cost to user's expenses and to list of all transactions (for use in GUI)
    public void addCost(Cost cost) {
        expenses.addCost(cost.getCategory(), cost.getDescription(), cost.getAmount());
    }

    // MODIFIES: this
    // EFFECTS: adds a fund to user's income
    public void addFund(Fund fund) {
        income.addFund(fund.getCategory(), fund.getDescription(), fund.getAmount());
    }

    public void addTransaction(Transaction transaction) {
        transactions.addTransaction(transaction.getCategory(), transaction.getDescription(), transaction.getAmount());
    }

    // EFFECTS: returns JSON representation of user
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", name);
        json.put("income", incomeToJson());
        json.put("expenses", expensesToJson());
        json.put("transactions", transactionsToJson());
        return json;
    }

    // EFFECTS: returns income in this user's budget as a JSON array
    private JSONArray incomeToJson() {
        JSONArray jsonArray;

        jsonArray = income.incomeToJson();

        return jsonArray;
    }

    // EFFECTS: returns expenses in this user's budget as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray;

        jsonArray = expenses.expensesToJson();

        return jsonArray;
    }

    // EFFECTS: returns expenses in this user's budget as a JSON array
    private JSONArray transactionsToJson() {
        JSONArray jsonArray;

        jsonArray = transactions.transactionsToJson();

        return jsonArray;
    }
}
