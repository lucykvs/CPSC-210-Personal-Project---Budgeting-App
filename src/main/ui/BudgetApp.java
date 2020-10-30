package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Budget application
public class BudgetApp {
    private static final String JSON_STORE = "./data/budget.json";
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
        System.out.println("\ts -> save budget to file");
        System.out.println("\tl -> load budget from file");
        System.out.println("\tq -> quit");
        System.out.println("\n\tType 'back' to return to username entry.");
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
        } else if (command.equals("s")) {
            saveBudget();
        } else if (command.equals("l")) {
            loadBudget();
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
            user.addCost(new Cost(category, description, amount));
        } else {
            System.out.println("Cannot have a negative cost.\n");
        }
    }

    // EFFECTS: prompts user to select category for their cost and returns it
    private CostCategory readCostCategory() {
        System.out.println("\nPlease select a category for your cost:\n");

        int menuLabel = 1;
        for (CostCategory c : CostCategory.values()) {
            System.out.println(menuLabel + ":" + getCostCatString(c));
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return CostCategory.values()[menuSelection - 1];
    }

    // EFFECTS: converts CostCategory to more appealing string
    public String getCostCatString(CostCategory c) {
        String catString = "";

        switch (c) {
            case BILLS:
                catString = "Bills";
                break;
            case DEBT_REPAYMENTS:
                catString = "Debt repayments";
                break;
            case ONE_TIME_EXPENSES:
                catString = "One-time expenses";
                break;
            case MISCELLANEOUS_PURCHASES:
                catString = "Miscellaneous expenses";
                break;
            case FOR_FUN:
                catString = "For fun!";
                break;
        }

        return catString;
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
            user.addFund(new Fund(category, description, amount));
        } else {
            System.out.println("Cannot have a negative income.\n");
        }
    }

    // EFFECTS: prompts user to select category for their source of income and returns it
    private FundCategory readFundCategory() {
        System.out.println("Please select a category for your source of income");

        int menuLabel = 1;
        for (FundCategory f : FundCategory.values()) {
            System.out.println(menuLabel + ":" + getFundCatString(f));
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return FundCategory.values()[menuSelection - 1];
    }

    // EFFECTS: converts FundCategory to more appealing string
    public String getFundCatString(FundCategory f) {
        String catString = "";

        switch (f) {
            case EMPLOYMENT:
                catString = "Employment";
                break;
            case LOAN:
                catString = "Loans";
                break;
            case GIFT:
                catString = "Gifts";
                break;
            case OTHER:
                catString = "Other";
                break;
        }

        return catString;
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
            System.out.println("Your total income is: $" + user.getTotalIncomeAmount());
            System.out.println("Your total expenses are: $" + user.getTotalExpenseAmount());
        } else if (selection.equals("balance")) {
            System.out.println("Your budget balance is: $" + user.getBudgetBalance());
        } else if (selection.equals("expenses")) {
            System.out.println("Your current expenses are:\n");
            printAllDescriptions(user.getExpenses().getAllCostDescriptions());
        } else if (selection.equals("income")) {
            System.out.println("Your current sources of income are:\n");
            printAllDescriptions(user.getIncome().getAllFundDescriptions());
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

