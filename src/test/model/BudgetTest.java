//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class BudgetTest {
//    private Budget budget;
//
//    @BeforeEach
//    public void setUp() {
//        budget = new Budget();
//    }
//
//    @Test
//    public void testBalanceBudgetEmptyBudget() {
//        double balance = budget.getBalance();
//
//        assertEquals(0, balance);
//    }
//
//    @Test
//    public void testBalanceBudgetSomeExpensesEmptyIncome() {
//        budget.getExpenses().addCost("Groceries", 150);
//        budget.getExpenses().addCost("Gas",90);
//
//        double balance = budget.getBalance();
//
//        assertEquals(budget.getIncome().getTotalIncome() - budget.getExpenses().getTotalExpenses(), balance);
//    }
//
//    @Test
//    public void testBalanceBudgetEmptyExpensesSomeIncome() {
//        budget.getIncome().addFund("Work", 2000);
//        budget.getIncome().addFund("Bursary",1200);
//
//        double balance = budget.getBalance();
//
//        assertEquals(budget.getIncome().getTotalIncome(), balance);
//    }
//
//    @Test
//    public void testBalanceBudgetSomeExpensesSomeIncome() {
//        budget.getExpenses().addCost("Groceries", 150);
//        budget.getExpenses().addCost("Gas",90);
//        budget.getIncome().addFund("Work", 2000);
//        budget.getIncome().addFund("Bursary",1200);
//
//        double balance = budget.getBalance();
//
//        assertEquals(budget.getIncome().getTotalIncome() - budget.getExpenses().getTotalExpenses(), balance);
//    }
//
//
//}
