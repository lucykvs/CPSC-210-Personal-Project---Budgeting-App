package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads user's budget from JSON data stored in file
// Note: JSON data persistence in this project was modeled off of JsonSerializationDemo, CPSC 210 2020 teaching team
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads username and budget from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses username and user's budget from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        User user = new User(username);
//        addExpenses(user, jsonObject);
//        addIncomes(user, jsonObject);
        addTransactions(user, jsonObject);
        return user;
    }

//    // MODIFIES: user
//    // EFFECTS: parses expenses from JSON object and adds them to user's budget
//    private void addExpenses(User user, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
//        for (Object json : jsonArray) {
//            JSONObject nextCost = (JSONObject) json;
//            addCost(user, nextCost);
//        }
//    }
//
//    // MODIFIES: user
//    // EFFECTS: parses incomes from JSON object and adds them to user's budget
//    private void addIncomes(User user, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("income");
//        for (Object json : jsonArray) {
//            JSONObject nextFund = (JSONObject) json;
//            addFund(user, nextFund);
//        }
//    }

    // MODIFIES: user
    // EFFECTS: parses incomes from JSON object and adds them to user's budget
    private void addTransactions(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("transactions");
        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addTransaction(user, nextTransaction);
        }
    }

//    // MODIFIES: user
//    // EFFECTS: parses cost from JSON object and adds it to user's budget
//    private void addCost(User user, JSONObject jsonObject) {
//        Category category = Category.valueOf(jsonObject.getString("category"));
//        String description = jsonObject.getString("description");
//        double amount = jsonObject.getDouble("amount");
//        user.addCost(category, description, amount);
//    }
//
//    // MODIFIES: user
//    // EFFECTS: parses fund from JSON object and adds it to user's income
//    private void addFund(User user, JSONObject jsonObject) {
//        Category category = Category.valueOf(jsonObject.getString("category"));
//        String description = jsonObject.getString("description");
//        double amount = jsonObject.getDouble("amount");
//        user.addFund(category, description, amount);
//    }

    // MODIFIES: user
    // EFFECTS: parses transaction from JSON object and adds it to user's income or expenses
    private void addTransaction(User user, JSONObject jsonObject) {
        Category category = Category.valueOf(jsonObject.getString("category"));
        String description = jsonObject.getString("description");
        double amount = jsonObject.getDouble("amount");
        String type = jsonObject.getString("type");
        Transaction t;

        if (type.equals("income")) {
            user.addFund(category, description, amount);
        } else {
            user.addCost(category, description, amount);
        }
    }
}