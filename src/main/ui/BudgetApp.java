package ui;

import model.Budget;
import model.User;

import java.util.List;
import java.util.Scanner;

// Budget application
public class BudgetApp {
    private Budget budget;
    private Scanner input;
    public User user;

    // EFFECTS: runs the budget application
    public BudgetApp() {
        runBudgetApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBudgetApp() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                init();
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
    private void init() {
        input = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = input.next();
        user = new User(username);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
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
        System.out.print("Enter cost description: ");
        String description = input.next();
        System.out.print("Enter amount of cost: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            user.budget.expenses.addCost(description, amount);
        } else {
            System.out.println("Cannot have a negative cost.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a fund to user's list of incomes
    public void addFund() {
        System.out.print("Enter income description: ");
        String description = input.next();               // as of now, will only work with one-word input
        System.out.print("Enter amount of income: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            user.budget.income.addFund(description, amount);
        } else {
            System.out.println("Cannot have a negative income.\n");
        }
    }

    // EFFECTS: prompts user to enter 'totals', 'balance', 'expenses', 'or income' to view details of their budget
    public void viewDetails() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("totals") || selection.equals("balance")
                || selection.equals("expenses") || selection.equals("income"))) {
            System.out.println("Type 'totals' for your total income and expenses");
            System.out.println("Type 'balance' for your budget balance");
            System.out.println("Type 'expenses' for a list of your expense descriptions");
            System.out.println("Type 'income' for a list of your sources of income");
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
}

