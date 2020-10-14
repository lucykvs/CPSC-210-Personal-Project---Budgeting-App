package main.ui;


import main.model.Cost;
import main.model.Expenses;
import main.model.Income;
import main.model.Incomes;

import java.util.Scanner;

// Budget application
public class BudgetApp {
    private Incomes incomes;
    private Expenses expenses;
    private Scanner input;

    // EFFECTS: runs the budget application
    public BudgetApp() {
        runBudgetApp();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBudgetApp() {
        boolean keepGoing = true;
        String command = null;

        // initialize user input
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            addCost();
        } else if (command.equals("i")) {
            addIncome();
        } else if (command.equals("b")) {
            getBalance();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> add cost");
        System.out.println("\ti -> add income");
        System.out.println("\tb -> get budget balance");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a cost to list of expenses
    private void addCost() {
        System.out.print("Enter cost description: ");
        String description = input.next();
        System.out.print("Enter amount of cost: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            expenses.addCost(description, amount);
        } else {
            System.out.println("Cannot have a negative cost.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an income to list of incomes
    private void addIncome() {
        System.out.print("Enter income description: ");
        String description = input.next();
        System.out.print("Enter amount of income: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            incomes.addIncome(description, amount);
        } else {
            System.out.println("Cannot have a negative income.\n");
        }
    }

    // EFFECTS: prompts user to select expenses, income, or budget balance and returns total of category
    private double selectView() {
        double total;
        String selection = "";  // force entry into loop

        while (!(selection.equals("e") || selection.equals("i") || selection.equals("b"))) {
            System.out.println("e for total expense amount");
            System.out.println("i for total income amount");
            System.out.println("b for budget balance");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("e")) {
            total = expenses.getTotalExpenses();
        } else if (selection.equals("i")) {
            total = incomes.getTotalIncome();
        } else {
            total = getBalance();
        }
        return total;
    }

    private double getBalance() {
        return (incomes.getTotalIncome() - expenses.getTotalExpenses());
    }

}

