package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a user with a username and a budget
public class User implements Writable {
    public String name;                   //username of account user
    public Budget budget;                 //budget of account user
    public Income income;
    public Expenses expenses;

    // EFFECTS: Constructs a user with given username and an empty budget
    public User(String username) {
        budget = new Budget();
        name = username;
        income = new Income();
        expenses = new Expenses();
    }

    // EFFECTS: returns name of user
    public String getName() {
        return name;
    }

    // EFFECTS: gets total income amount of this user
    public double getTotalIncomeAmount() {
        return income.getTotalIncome();
    }

    public double getTotalExpenseAmount() {
        return expenses.getTotalExpenses();
    }

    // EFFECTS: returns the balance of budget's income and expenses. If balance is positive, income > expenses.
    // If balance is negative, expenses > income.
    public double getBudgetBalance() {
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


    // MODIFIES: this
    // EFFECTS: adds cost to this user's budget
    public void addCost(Cost cost) {
        expenses.addCost(cost.getCategory(), cost.getDescription(), cost.getAmount());
    }

    // MODIFIES: this
    // EFFECTS: adds fund to this user's income
    public void addFund(Fund fund) {
        income.addFund(fund.getCategory(), fund.getDescription(), fund.getAmount());
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", name);
        json.put("income", incomeToJson());
        json.put("expenses", expensesToJson());
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
}
