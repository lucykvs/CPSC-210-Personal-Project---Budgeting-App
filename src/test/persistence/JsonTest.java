package persistence;


import model.Category;
import model.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCost(Category category, String description, double amount, Transaction cost) {
        assertEquals(category, cost.getCategory());
        assertEquals(description, cost.getDescription());
        assertEquals(amount, cost.getAmount());
    }

    protected void checkFund(Category category, String description, double amount, Transaction fund) {
        assertEquals(category, fund.getCategory());
        assertEquals(description, fund.getDescription());
        assertEquals(amount, fund.getAmount());
    }
}
