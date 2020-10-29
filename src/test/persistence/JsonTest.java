package persistence;

import model.Cost;
import model.CostCategory;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkCost(CostCategory category, String description, double amount, Cost cost) {
        assertEquals(category, cost.getCategory());
        assertEquals(description, cost.getDescription());
        assertEquals(amount, cost.getAmount());
    }
}
