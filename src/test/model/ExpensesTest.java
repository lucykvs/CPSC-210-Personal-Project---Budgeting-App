package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpensesTest {

    @Test
    public void testAddCost() {
        Expenses myExpenses = new Expenses();  //empty collection
        List<String> noDescriptions = myExpenses.getAllCostDescriptions();
        assertEquals(0, noDescriptions.size());

        myExpenses.addCost("Work", 2000);
        myExpenses.addCost("Bursary",1200);

        List<String> descriptions = myExpenses.getAllCostDescriptions();
        assertEquals(2, descriptions.size());
    }

    @Test
    public void testGetAllCostDescriptionsEmpty() {
        Expenses expenses = new Expenses();  //empty collection
        List<String> descriptions = expenses.getAllCostDescriptions();
        assertEquals(0, descriptions.size());
    }

    @Test
    public void testGetAllCostDescriptionsSomeCosts() {
        Expenses myExpenses = new Expenses();  //empty collection
        List<String> noDescriptions = myExpenses.getAllCostDescriptions();
        assertEquals(0, noDescriptions.size());

        myExpenses.addCost("Groceries", 150);
        myExpenses.addCost("Gas",90);

        List<String> descriptions = myExpenses.getAllCostDescriptions();
        assertEquals(2, descriptions.size());
    }

    @Test
    public void testGetAllCostDescriptionsManyCosts() {
        Expenses myExpenses = new Expenses();  //empty collection
        List<String> noDescriptions = myExpenses.getAllCostDescriptions();
        assertEquals(0, noDescriptions.size());

        for (int i = 0; i < 10000; i++) {
            myExpenses.addCost("fund", 200);
        }

        List<String> descriptions = myExpenses.getAllCostDescriptions();
        assertEquals(10000, descriptions.size());
    }



    @Test
    public void testGetTotalExpensesEmpty() {
        Expenses myExpenses = new Expenses();  //empty collection
        assertEquals(0, myExpenses.getTotalExpenses());
    }

    @Test
    public void testGetTotalExpensesEmptySomeCosts() {
        Expenses myExpenses = new Expenses();  //empty collection

        myExpenses.addCost("Groceries", 200);
        myExpenses.addCost("Gas",90.50);
        myExpenses.addCost("Tuition",3000);

        assertEquals(200 + 90.50 + 3000, myExpenses.getTotalExpenses());
    }

    @Test
    public void testGetTotalExpensesManyCosts() {
        Expenses myExpenses = new Expenses();  //empty collection
        double count = 0;

        for (int i = 0; i < 10000; i++) {
            myExpenses.addCost("fund", 5);
            count = count + 5;
            assertEquals(count, myExpenses.getTotalExpenses());
        }
    }
}
