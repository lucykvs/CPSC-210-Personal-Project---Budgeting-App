package model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Transactions {
//    private User user;
    private ArrayList<Transaction> transactions;

    // EFFECTS: constructs an empty collection of costs
    public Transactions() {
        transactions = new ArrayList<>();
//        this.user = user;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds a new transaction to the collection of transactions
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public boolean removeTransaction(Transaction t) {
        return transactions.remove(t);
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds a new transaction to the collection of transactions
    public void addCost(Category category, String description, double amount) {
        Transaction t = new Cost(category, description, amount);
        transactions.add(t);
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds a new transaction to the collection of transactions
    public void addFund(Category category, String description, double amount) {
        Transaction t = new Fund(category, description, amount);
        transactions.add(t);
    }

    public double getTotalTransactions() {
        double total = 0;

        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
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

    // EFFECTS: return JSON representation of transactions
    public JSONArray transactionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Transaction t : transactions) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
