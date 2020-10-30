package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;
    private final CostCategory cc1 = CostCategory.BILLS;
    private final FundCategory fc1 = FundCategory.EMPLOYMENT;
    private final FundCategory fc4 = FundCategory.OTHER;

    @BeforeEach
    public void setUp() {
        user = new User("Lucy");
    }

    @Test
    public void testConstructor() {
        assertEquals("Lucy", user.getName());
        assertEquals(user.getIncome().getTotalIncome(), 0);
        assertEquals(user.getExpenses().getTotalExpenses(), 0);
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

        assertEquals(user.getIncome().getTotalIncome() - user.getExpenses().getTotalExpenses(), balance);
    }

    @Test
    public void testBalanceBudgetEmptyExpensesSomeIncome() {
        user.getIncome().addFund(fc1,"Work", 2000);
        user.getIncome().addFund(fc4,"Bursary",1200);

        double balance = user.getBudgetBalance();

        assertEquals(user.getIncome().getTotalIncome(), balance);
    }

    @Test
    public void testBalanceBudgetSomeExpensesSomeIncome() {
        user.getExpenses().addCost(cc1,"Groceries", 150);
        user.getExpenses().addCost(cc1,"Gas",90);
        user.getIncome().addFund(fc1,"Work", 2000);
        user.getIncome().addFund(fc4,"Bursary",1200);

        double balance = user.getBudgetBalance();

        assertEquals(user.getIncome().getTotalIncome() - user.getExpenses().getTotalExpenses(), balance);
    }
}

