package model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private ArrayList<Transaction> transactions;

    // EFFECTS: constructs an empty collection of costs
    public Transactions() {
        transactions = new ArrayList<>();
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds a new transaction to the collection of transactions
    public void addTransaction(Category category, String description, double amount) {
        Transaction t = new Transaction(category, description, amount);
        transactions.add(t);
    }

    // MODIFIES: this
    // EFFECTS: returns list of the descriptions of those transactions in list of expenses;
    // list returned can be empty.
    public List<String> getAllTransactionDescriptions() {
        List<String> descriptions = new ArrayList<>();

        for (Transaction t : transactions) {
            descriptions.add(t.getDescription());
        }
        return descriptions;
    }

    // EFFECTS: returns list of costs in expenses
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // EFFECTS: return JSON representation of expenses
    public JSONArray transactionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Transaction t : transactions) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
