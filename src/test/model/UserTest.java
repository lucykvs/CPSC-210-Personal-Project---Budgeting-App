package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    private final Category cc1 = Category.BILLS;
    private final Category cc2 = Category.DEBT_REPAYMENTS;
    private final Category fc1 = Category.EMPLOYMENT;
    private final Category fc4 = Category.OTHER;

    @BeforeEach
    public void setUp() {
        user = new User("Lucy");
    }

    @Test
    public void testConstructor() {
        assertEquals("Lucy", user.getName());
        assertEquals(user.getIncome().getTotalTransactions(), 0);
        assertEquals(user.getExpenses().getTotalTransactions(), 0);
        assertEquals(user.getAllTransactions().getTotalTransactions(),0);
        assertEquals(user.getAllTransactions().getTransactions().size(), 0);
    }

    @Test
    public void testGetTotalIncomeAmountEmptyIncome() {
        assertEquals(0,user.getTotalIncomeAmount());
    }

    @Test
    public void testGetTotalIncomeAmountSomeIncome() {
        user.getIncome().addFund(fc1,"Work", 2000);
        user.getIncome().addFund(fc4,"Bursary",1200);

        assertEquals(2000+1200,user.getTotalIncomeAmount());
    }

    @Test
    public void testGetTotalExpenseAmountEmptyExpenses() {
        assertEquals(0,user.getTotalExpenseAmount());
    }

    @Test
    public void testGetTotalExpenseAmountSomeExpenses() {
        try {
            user.addCost(cc1, "Groceries", 150);
            user.addCost(cc1, "Gas", 90);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }
        assertEquals(2, user.getAllTransactions().getTransactions().size());

        assertEquals(150+90,user.getTotalExpenseAmount());
    }

    @Test
    public void testGetBudgetBalanceEmptyBudget() {
        double balance = user.getBudgetBalance();

        assertEquals(0, balance);
    }

    @Test
    public void testGetBudgetBalanceSomeExpensesEmptyIncome() {
        try {
            user.addCost(cc1, "Groceries", 150);
            user.addCost(cc1, "Gas", 90);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }

        double balance = user.getBudgetBalance();

        assertEquals(user.getIncome().getTotalTransactions() - user.getExpenses().getTotalTransactions(), balance);
    }

    @Test
    public void testBalanceBudgetEmptyExpensesSomeIncome() {
        try {
            user.addFund(fc1, "Work", 2000);
            user.addFund(fc4, "Bursary", 1200);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }

        double balance = user.getBudgetBalance();

        assertEquals(user.getIncome().getTotalTransactions(), balance);
    }

    @Test
    public void testBalanceBudgetSomeExpensesSomeIncome() {
        try {
            user.addCost(cc1, "Groceries", 150);
            user.addCost(cc1, "Gas", 90);
            user.addFund(fc1, "Work", 2000);
            user.addFund(fc4, "Bursary", 1200);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }

        double balance = user.getBudgetBalance();

        assertEquals(user.getIncome().getTotalTransactions() - user.getExpenses().getTotalTransactions(), balance);
    }

    @Test
    public void testToJsonUserWithEmptyBudget() {
        JSONObject json = user.toJson();

        String username = json.getString("username");
        JSONArray transactions = json.getJSONArray("transactions");
        assertEquals("Lucy", username);
        assertEquals(0,transactions.length());
    }


    @Test
    public void testToJsonUserWithGeneralBudget() {
        try {
            user.addCost(cc1, "Groceries", 150);
            user.addCost(cc2, "Student loan payments", 200);
            user.addFund(fc1, "Work", 2000);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }
        JSONObject json = user.toJson();

        String username = json.getString("username");
        JSONArray transactions = json.getJSONArray("transactions");

        assertEquals("Lucy",username);
        assertEquals(3, transactions.length());
    }

    @Test
    public void testRemoveTransactionNoException() {
        try {
        user.addFund(fc1,"Work", 2000);
        user.addFund(fc4,"Bursary",1200);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }

        assertEquals(2, user.getAllTransactions().getTransactions().size());

        user.removeTransaction(new Fund(fc1, "Work", 2000));

        assertEquals(1, user.getAllTransactions().getTransactions().size());
        assertEquals(1, user.getIncome().getTransactions().size());
    }

    @Test
    public void testRemoveTransactionNegativeInputExceptionThrown() {
        try {
            user.addFund(fc1,"Work", 2000);
            user.addFund(fc4,"Bursary",1200);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }

        assertEquals(2, user.getAllTransactions().getTransactions().size());

        user.removeTransaction(new Fund(fc1, "Work", -2000));

        assertEquals(2, user.getAllTransactions().getTransactions().size());
        assertEquals(2, user.getIncome().getTransactions().size());
    }

    @Test
    public void testAddFundNoExceptionThrown() {
        try {
            user.addFund(fc1,"Work", 2000);
            user.addFund(fc4,"Bursary",1200);
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }

        assertEquals(2, user.getAllTransactions().getTransactions().size());
    }

    @Test
    public void testAddFundNegativeInputExceptionThrownOneAdded() {
        try {
            user.addFund(fc1, "Work", 2000);
            user.addFund(fc4, "Bursary", -1200);
            fail("NegativeAmountException should have been thrown.");
        } catch (NumberFormatException e) {
            fail("NumberFormatException thrown, should have thrown NegativeAmountException");
        } catch (NegativeAmountException e) {
            // expected behaviour
        }

        assertEquals(1, user.getAllTransactions().getTransactions().size());
    }

    @Test
    public void testAddFundNegativeInputExceptionThrownNoneAdded() {
        try {
            user.addFund(fc1,"Work", -2000);
            user.addFund(fc4,"Bursary",1200);
            fail("NumberFormatException should have been thrown.");
        } catch (NegativeAmountException e) {
            // expected behaviour
        }

        assertEquals(0, user.getAllTransactions().getTransactions().size());
    }

    @Test
    public void testAddCostNoExceptionThrown() {
        try {
            user.addCost(cc1,"Rent", 800);
            user.addCost(cc2,"Student loan payments",1200);
        } catch (Exception e) {
            fail("NegativeAmountException thrown, should not have thrown exception.");
        }

        assertEquals(2, user.getAllTransactions().getTransactions().size());
    }

    @Test
    public void testAddCostNegativeInputExceptionThrownOneAdded() {
        try {
            user.addCost(cc1, "Rent", 800);
            user.addCost(cc2, "Student loan payments", -1200);
            fail("NegativeAmountException should have been thrown.");
        } catch (NumberFormatException e) {
            fail("NumberFormatException thrown, should have thrown NegativeAmountException.");
        } catch (NegativeAmountException e) {
            // expected behaviour
        }

        assertEquals(1, user.getAllTransactions().getTransactions().size());
    }

    @Test
    public void testAddCostNegativeInputExceptionThrownNoneAdded() {
        try {
            user.addCost(cc1, "Rent", -800);
            user.addCost(cc2, "Student loan payments", 1200);
            fail("NegativeInputException should have been thrown.");
        } catch (NumberFormatException e) {
            fail("NumberFormatException thrown, should have thrown NegativeAmountException");
        } catch (NegativeAmountException e) {
            // expected behaviour
        }

        assertEquals(0, user.getAllTransactions().getTransactions().size());
    }
}

