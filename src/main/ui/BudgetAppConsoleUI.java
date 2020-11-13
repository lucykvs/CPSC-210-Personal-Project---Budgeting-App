package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Budget application
// Note: JSON data persistence in this project was modeled off of JsonSerializationDemo, CPSC 210 2020 teaching team
public class BudgetAppConsoleUI {
    private static final String JSON_STORE = "./data/budget.json";
    private Scanner input;
    private User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs console-based UI of budget application
    public static void main(String[] args) {
        new BudgetAppConsoleUI();
    }

    // EFFECTS: runs the budget application
    public BudgetAppConsoleUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBudgetApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBudgetApp() {
        System.out.println("Welcome to your budgeting application!");
        boolean keepGoing = true;
        String command;

        input = new Scanner(System.in);
        String username = displayLoadMenu();

        while (keepGoing) {
            displayMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                username = displayLoadMenu();
            } else if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nBye for now!");
    }

    // MODIFIES: this
    // EFFECTS: gives user option to create new user or load previous
    private String displayLoadMenu() {
        System.out.println("\nChoose from the following options:");
        System.out.println("\tc -> create new user");
        System.out.println("\tl -> load previous user's budget\n");

        String command = input.next();
        String username = " ";

        if (command.equals("c")) {
            username = initNewUser();
        } else if (command.equals("l")) {
            loadUser();
            username = user.name;
            System.out.println("\tWelcome back " + username + "!");
        }
        return username;
    }

    // MODIFIES: this
    // EFFECTS: prompts user for username and sets this user's name
    private String initNewUser() {
        System.out.print("\tEnter your username: ");
        String username = (input.next() + input.nextLine());
        user = new User(username);
        System.out.println("\nHi " + username + "! You are currently working on your monthly budget.");
        return username;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> add cost");
        System.out.println("\ti -> add income");
        System.out.println("\tv -> view details");
        System.out.println("\ts -> save budget to file");
        System.out.println("\tq -> quit");
        System.out.println("\n\tType 'back' to return to username entry.\n");
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
            saveUser();
        } else {
            System.out.println("\tSelection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a cost to user's list of expenses
    public void addCost() {
        CostCategory category = readCostCategory();
        System.out.print("\tEnter cost description: ");
        String description = (input.next() + input.nextLine());
        System.out.print("\tEnter amount of cost: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            user.addCost(new Cost(category, description, amount));
        } else {
            System.out.println("\tCannot have a negative cost.\n");
        }
    }

    // EFFECTS: prompts user to select category for their cost and returns it
    private CostCategory readCostCategory() {
        System.out.println("\nPlease select a category for your cost:\n");

        int menuLabel = 1;
        for (CostCategory c : CostCategory.values()) {
            System.out.println("\t" + menuLabel + " -> " + getCostCatString(c));
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
        System.out.print("\tEnter income description: ");
        String description = (input.next() + input.nextLine());
        System.out.print("\tEnter amount of income: $");
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
            System.out.println("\t" + menuLabel + " -> " + getFundCatString(f));
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
        String select = "";  // force entry into loop

        while (!(select.equals("t") || select.equals("b")
                || select.equals("e") || select.equals("i"))) {
            System.out.println("\nSelect from the following options to view more details:\n");
            System.out.println("\tt -> for total income and expense amounts");
            System.out.println("\tb -> for your budget balance");
            System.out.println("\te -> for a list of descriptions of your expenses");
            System.out.println("\ti -> for a list of descriptions of your sources of income");
            select = input.next();
            select = select.toLowerCase();
            printDetails(select);
        }
    }

    // EFFECTS: prints income and expense totals, budget balance, or list of expense or income descriptions
    public void printDetails(String selection) {
        if (selection.equals("t")) {
            System.out.println("\tYour total income is: $" + user.getTotalIncomeAmount());
            System.out.println("\tYour total expenses are: $" + user.getTotalExpenseAmount());
        } else if (selection.equals("b")) {
            System.out.println("\tYour budget balance is: $" + user.getBudgetBalance());
        } else if (selection.equals("e")) {
            System.out.println("\tYour current expenses are:\n");
            printAllDescriptions(user.getExpenses().getAllCostDescriptions());
        } else if (selection.equals("i")) {
            System.out.println("\tYour current sources of income are:\n");
            printAllDescriptions(user.getIncome().getAllFundDescriptions());
        } else {
            System.out.println("\tSelection not valid...\n");
        }
    }

    // EFFECTS: prints descriptions from given list of descriptions in a numbered list
    private void printAllDescriptions(List<String> allDescriptions) {
        int i = 1;

        for (String d : allDescriptions) {
            System.out.println(i + ". " + d);
            i++;
        }
    }

    // EFFECTS: saves the user's budget information to a file
    private void saveUser() {
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
    private void loadUser() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getName() + "'s budget from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

