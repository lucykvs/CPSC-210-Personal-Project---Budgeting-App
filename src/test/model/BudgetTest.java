package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetTest {
    private Budget budget;
    private final CostCategory cc1 = CostCategory.BILLS;
    private final FundCategory fc1 = FundCategory.EMPLOYMENT;
    private final FundCategory fc4 = FundCategory.OTHER;

    @BeforeEach
    public void setUp() {
        budget = new Budget();
    }

    @Test
    public void testBalanceBudgetEmptyBudget() {
        double balance = budget.getBalance();

        assertEquals(0, balance);
    }

    @Test
    public void testBalanceBudgetSomeExpensesEmptyIncome() {
        budget.getExpenses().addCost(cc1,"Groceries", 150);
        budget.getExpenses().addCost(cc1,"Gas",90);

        double balance = budget.getBalance();

        assertEquals(budget.getIncome().getTotalIncome() - budget.getExpenses().getTotalExpenses(), balance);
    }

    @Test
    public void testBalanceBudgetEmptyExpensesSomeIncome() {
        budget.getIncome().addFund(fc1,"Work", 2000);
        budget.getIncome().addFund(fc4,"Bursary",1200);

        double balance = budget.getBalance();

        assertEquals(budget.getIncome().getTotalIncome(), balance);
    }

    @Test
    public void testBalanceBudgetSomeExpensesSomeIncome() {
        budget.getExpenses().addCost(cc1,"Groceries", 150);
        budget.getExpenses().addCost(cc1,"Gas",90);
        budget.getIncome().addFund(fc1,"Work", 2000);
        budget.getIncome().addFund(fc4,"Bursary",1200);

        double balance = budget.getBalance();

        assertEquals(budget.getIncome().getTotalIncome() - budget.getExpenses().getTotalExpenses(), balance);
    }


}
