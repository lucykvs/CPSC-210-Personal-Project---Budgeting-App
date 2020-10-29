package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

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

    // EFFECTS: returns budget of user
    public Budget getBudget() {
        return budget;
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
