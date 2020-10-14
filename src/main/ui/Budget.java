package main.ui;

import main.model.Expenses;
import main.model.Income;

import java.util.List;
import java.util.Scanner;

// Represents a budget with a list of expenses, list of incomes, and a username
public class Budget {
    private Income income;
    private Expenses expenses;
    private String username;
    private Scanner input;


    // EFFECTS: constructs a budget with an empty list of expenses, empty list
    // of incomes, and a username
    public Budget(String username) {
        income = new Income();
        expenses = new Expenses();
        this.username = username;
        input = new Scanner(System.in);
    }


    // MODIFIES: this
    // EFFECTS: adds a cost to list of expenses
    public void addCost() {
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
    // EFFECTS: adds a fund to list of incomes
    public void addFund() {
        System.out.print("Enter income description: ");
        String description = input.next();               // as of now, will only work with one-word input
        System.out.print("Enter amount of income: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            income.addFund(description, amount);
        } else {
            System.out.println("Cannot have a negative income.\n");
        }
    }


    private double getBalance() {
        return (income.getTotalIncome() - expenses.getTotalExpenses());
    }


    // EFFECTS: prompts user to select expenses, income, or budget balance and returns total of category
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

    public void printDetails(String selection) {
        if (selection.equals("totals")) {
            System.out.println("Your total income is: $" + income.getTotalIncome());
            System.out.println("Your total expenses are: $" + expenses.getTotalExpenses());
        } else if (selection.equals("balance")) {
            System.out.println("Your budget balance is: $" + getBalance());
        } else if (selection.equals("expenses")) {
            System.out.println("Your current expenses are:\n");
            printAllDescriptions(expenses.getAllCostDescriptions());
        } else if (selection.equals("income")) {
            System.out.println("Your current sources of income are:\n");
            printAllDescriptions(income.getAllFundDescriptions());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void printAllDescriptions(List<String> allDescriptions) {
        int i = 1;

        for (String d : allDescriptions) {
            System.out.println(i + "." + d);
            i++;
        }
    }

}
