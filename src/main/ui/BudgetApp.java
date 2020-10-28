package ui;

import jdk.jfr.Category;
import model.Budget;
import model.CostCategory;
import model.FundCategory;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Budget application
public class BudgetApp {
    private static final String JSON_STORE = "./data/budget.json";
    private Budget budget;
    private Scanner input;
    public User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the budget application
    public BudgetApp() {
        System.out.println("Welcome to your budgeting application!");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBudgetApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBudgetApp() {
        boolean keepGoing = true;
        String command;

        String username = init();

        while (keepGoing) {
            displayMenu(username);

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                username = init();
            } else if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nBye for now!");
    }

    // MODIFIES: this
    // EFFECTS: prompts user for username and sets this user's name
    private String init() {
        input = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = (input.next() + input.nextLine());
        user = new User(username);
        System.out.println("\nHi " + username + "! You are currently working on your monthly budget.");
        return username;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu(String username) {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> add cost");
        System.out.println("\ti -> add income");
        System.out.println("\tv -> view details");
        System.out.println("\tq -> quit");
        System.out.println("\tType 'back' to return to username entry.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command from first menu
    private void processCommand(String command) {
        if (command.equals("c")) {
            addCost();
        } else if (command.equals("i")) {
            addFund();
        } else if (command.equals("v")) {
            viewDetails();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a cost to user's list of expenses
    public void addCost() {
        CostCategory category = readCostCategory();
        System.out.print("Enter cost description: ");
        String description = (input.next() + input.nextLine());
        System.out.print("Enter amount of cost: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            user.budget.expenses.addCost(category, description, amount);
        } else {
            System.out.println("Cannot have a negative cost.\n");
        }
    }

    // EFFECTS: prompts user to select category for their cost and returns it
    private CostCategory readCostCategory() {
        System.out.println("Please select a category for your cost");

        int menuLabel = 1;
        for (CostCategory c : CostCategory.values()) {
            System.out.println(menuLabel + ":" + c);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return CostCategory.values()[menuSelection - 1];
    }

    // MODIFIES: this
    // EFFECTS: adds a fund to user's list of incomes
    public void addFund() {
        FundCategory category = readFundCategory();
        System.out.print("Enter income description: ");
        String description = (input.next() + input.nextLine());
        System.out.print("Enter amount of income: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            user.budget.income.addFund(category, description, amount);
        } else {
            System.out.println("Cannot have a negative income.\n");
        }
    }

    // EFFECTS: prompts user to select category for their source of income and returns it
    private FundCategory readFundCategory() {
        System.out.println("Please select a category for your source of income");

        int menuLabel = 1;
        for (FundCategory c : FundCategory.values()) {
            System.out.println(menuLabel + ":" + c);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return FundCategory.values()[menuSelection - 1];
    }


    // EFFECTS: prompts user to enter 'totals', 'balance', 'expenses', 'or income' to view details of their budget
    public void viewDetails() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("totals") || selection.equals("balance")
                || selection.equals("expenses") || selection.equals("income"))) {
            System.out.println("Type 'totals' for your total income and expense amounts");
            System.out.println("Type 'balance' for your budget balance");
            System.out.println("Type 'expenses' for a list of descriptions of your expenses");
            System.out.println("Type 'income' for a list of descriptions of your sources of income");
            selection = input.next();
            selection = selection.toLowerCase();
            printDetails(selection);
        }
    }

    // EFFECTS: prints income and expense totals, budget balance, or list of expense or income descriptions
    public void printDetails(String selection) {
        if (selection.equals("totals")) {
            System.out.println("Your total income is: $" + user.getBudget().getIncome().getTotalIncome());
            System.out.println("Your total expenses are: $" + user.getBudget().getExpenses().getTotalExpenses());
        } else if (selection.equals("balance")) {
            System.out.println("Your budget balance is: $" + user.getBudget().getBalance());
        } else if (selection.equals("expenses")) {
            System.out.println("Your current expenses are:\n");
            printAllDescriptions(user.getBudget().getExpenses().getAllCostDescriptions());
        } else if (selection.equals("income")) {
            System.out.println("Your current sources of income are:\n");
            printAllDescriptions(user.getBudget().getIncome().getAllFundDescriptions());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: prints descriptions from given list of descriptions in a numbered list
    private void printAllDescriptions(List<String> allDescriptions) {
        int i = 1;

        for (String d : allDescriptions) {
            System.out.println(i + "." + d);
            i++;
        }
    }

    // EFFECTS: saves the user's budget information to a file
    private void saveBudget() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved " + user.getName() + "'s budget to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads user and budget information from file
    private void loadBudget() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getName() + "'s budget from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

