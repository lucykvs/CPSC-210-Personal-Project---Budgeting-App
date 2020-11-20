package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        user.addCost(cc1,"Groceries", 150);
        user.addCost(cc1,"Gas",90);
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
        user.getExpenses().addCost(cc1,"Groceries", 150);
        user.getExpenses().addCost(cc1,"Gas",90);

        double balance = user.getBudgetBalance();

        assertEquals(user.getIncome().getTotalTransactions() - user.getExpenses().getTotalTransactions(), balance);
    }

    @Test
    public void testBalanceBudgetEmptyExpensesSomeIncome() {
        user.getIncome().addFund(fc1,"Work", 2000);
        user.getIncome().addFund(fc4,"Bursary",1200);

        double balance = user.getBudgetBalance();

        assertEquals(user.getIncome().getTotalTransactions(), balance);
    }

    @Test
    public void testBalanceBudgetSomeExpensesSomeIncome() {
        user.getExpenses().addCost(cc1,"Groceries", 150);
        user.getExpenses().addCost(cc1,"Gas",90);
        user.getIncome().addFund(fc1,"Work", 2000);
        user.getIncome().addFund(fc4,"Bursary",1200);

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
        user.addCost(cc1,"Groceries", 150);
        user.addCost(cc2,"Student loan payments", 200);
        user.addFund(fc1,"Work", 2000);
        JSONObject json = user.toJson();

        String username = json.getString("username");
        JSONArray transactions = json.getJSONArray("transactions");

        assertEquals("Lucy",username);
        assertEquals(3, transactions.length());
    }
}

