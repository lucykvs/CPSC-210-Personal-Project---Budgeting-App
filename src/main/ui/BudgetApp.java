package ui;

import java.util.Scanner;

// Budget application
public class BudgetApp {
    private Budget budget;
    private Scanner input;

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

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nBye for now!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            budget.addCost();
        } else if (command.equals("i")) {
            budget.addFund();
        } else if (command.equals("v")) {
            budget.viewDetails();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        input = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = input.next();
        budget = new Budget(username);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> add cost");
        System.out.println("\ti -> add income");
        System.out.println("\tv -> view details");
        System.out.println("\tq -> quit");
    }

}

