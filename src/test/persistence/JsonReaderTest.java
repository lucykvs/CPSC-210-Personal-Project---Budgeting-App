package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    private final CostCategory cc1 = CostCategory.BILLS;
    private final CostCategory cc3 = CostCategory.ONE_TIME_EXPENSES;
    private final FundCategory fc1 = FundCategory.EMPLOYMENT;
    private final FundCategory fc2 = FundCategory.LOAN;

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBudget() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBudget.json");
        try {
            User user = reader.read();
            assertEquals("Lucy", user.getName());
            assertEquals(0, user.income.getFunds().size());
            assertEquals(0, user.expenses.getCosts().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBudget.json");
        try {
            User user = reader.read();
            assertEquals("Lucy", user.getName());
            List<Fund> funds = user.income.getFunds();
            List<Cost> costs = user.expenses.getCosts();
            assertEquals(2, funds.size());
            assertEquals(2, costs.size());
            checkFund(fc2,"Student loans", 2400.00, funds.get(0));
            checkFund(fc1,"Las Margs", 1222.75, funds.get(1));
            checkCost(cc3,"Tuition", 3050, costs.get(0));
            checkCost(cc1,"Rent", 850, costs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
