package model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

// Represents a list of transactions
public class Transactions {
    private ArrayList<Transaction> transactions;

    // EFFECTS: constructs an empty collection of costs
    public Transactions() {
        transactions = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a given transaction to the collection of transactions
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    // MODIFIES: this
    // EFFECTS: if given transaction is in transactions, removes transaction from this and returns true; if not in this,
    //          returns false
    public boolean removeTransaction(Transaction t) {
        return transactions.remove(t);
    }

    // MODIFIES: this
    // EFFECTS: adds a new cost to the collection of transactions
    public void addCost(Category category, String description, double amount) {
        Transaction t = new Cost(category, description, amount);
        transactions.add(t);
    }

    // MODIFIES: this
    // EFFECTS: adds a new fund to the collection of transactions
    public void addFund(Category category, String description, double amount) {
        Transaction t = new Fund(category, description, amount);
        transactions.add(t);
    }

    // EFFECTS: returns total amount of this transactions
    public double getTotalTransactions() {
        double total = 0;

        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }

    // MODIFIES: this
    // EFFECTS: returns list of the descriptions of transactions in this transactions; list returned can be empty
    public List<String> getAllTransactionDescriptions() {
        List<String> descriptions = new ArrayList<>();

        for (Transaction t : transactions) {
            descriptions.add(t.getDescription());
        }
        return descriptions;
    }

    // EFFECTS: returns list of transactions in this transactions
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
