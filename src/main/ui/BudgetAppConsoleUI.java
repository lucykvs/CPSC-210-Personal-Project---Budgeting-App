package ui;

import model.Category;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumSet;
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
    private EnumSet<Category> costCategories = EnumSet.of(Category.BILLS,Category.DEBT_REPAYMENTS,
            Category.ONE_TIME_EXPENSES, Category.MISCELLANEOUS_PURCHASES, Category.FOR_FUN);
    private EnumSet<Category> fundCategories = EnumSet.of(Category.EMPLOYMENT,Category.LOAN, Category.GIFT,
            Category.OTHER);

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
        Category category = readCostCategory();
        System.out.print("\tEnter cost description: ");
        String description = (input.next() + input.nextLine());
        System.out.print("\tEnter amount of cost: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            user.addCost(category, description, amount);
        } else {
            System.out.println("\tCannot have a negative cost.\n");
        }
    }

    // EFFECTS: prompts user to select category for their cost and returns it
    private Category readCostCategory() {
        System.out.println("\nPlease select a category for your cost:\n");

        int menuLabel = 1;
        for (Category c : costCategories) {
            System.out.println("\t" + menuLabel + " -> " + Category.getCatString(c));
            menuLabel++;
        }
        int menuSelection = input.nextInt();

        Category cat = getSelectedCategory(menuSelection);
        return cat;
    }

    private Category getSelectedCategory(int menuSelection) {
        Category cat;

        switch (menuSelection) {
            case 1: cat = Category.BILLS;
                break;
            case 2: cat = Category.DEBT_REPAYMENTS;
                break;
            case 3: cat = Category.ONE_TIME_EXPENSES;
                break;
            case 4: cat = Category.MISCELLANEOUS_PURCHASES;
                break;
            default: cat = Category.FOR_FUN;
                break;
        }
        return cat;
    }

    // MODIFIES: this
    // EFFECTS: adds a fund to user's list of incomes
    public void addFund() {
        Category category = readFundCategory();
        System.out.print("\tEnter income description: ");
        String description = (input.next() + input.nextLine());
        System.out.print("\tEnter amount of income: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            user.addFund(category, description, amount);
        } else {
            System.out.println("Cannot have a negative income.\n");
        }
    }

    // EFFECTS: prompts user to select category for their source of income and returns it
    private Category readFundCategory() {
        System.out.println("Please select a category for your source of income");

        int menuLabel = 1;
        for (Category f : fundCategories) {
            System.out.println("\t" + menuLabel + " -> " + Category.getCatString(f));
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        Category cat;

        switch (menuSelection) {
            case 1:
                cat = Category.EMPLOYMENT;
                break;
            case 2:
                cat = Category.LOAN;
                break;
            case 3:
                cat = Category.GIFT;
                break;
            default:
                cat = Category.OTHER;
                break;
        }
        return cat;
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
            printAllDescriptions(user.getExpenses().getAllTransactionDescriptions());
        } else if (selection.equals("i")) {
            System.out.println("\tYour current sources of income are:\n");
            printAllDescriptions(user.getIncome().getAllTransactionDescriptions());
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

