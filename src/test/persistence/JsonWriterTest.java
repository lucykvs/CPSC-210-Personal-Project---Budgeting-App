package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
    private final CostCategory cc1 = CostCategory.BILLS;
    private final CostCategory cc3 = CostCategory.ONE_TIME_EXPENSES;
    private final FundCategory fc1 = FundCategory.EMPLOYMENT;
    private final FundCategory fc2 = FundCategory.LOAN;

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("Lucy");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBudget() {
        try {
            User user = new User("Lucy");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBudget.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBudget.json");
            user = reader.read();
            assertEquals("Lucy", user.getName());
            assertEquals(0, user.income.getFunds().size());
            assertEquals(0, user.expenses.getCosts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBudget() {
        try {
            User user = new User("Lucy");
            user.addFund(new Fund(fc1,"Las Margs", 1222.75));
            user.addFund(new Fund(fc2, "Student loans", 2400.00));
            user.addCost(new Cost(cc1, "Rent", 850));
            user.addCost(new Cost(cc3, "Tuition", 3050));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBudget.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBudget.json");
            user = reader.read();
            assertEquals("Lucy", user.getName());
            List<Fund> funds = user.income.getFunds();
            List<Cost> costs = user.expenses.getCosts();
            assertEquals(2, funds.size());
            assertEquals(2, costs.size());
            checkFund(fc1,"Las Margs", 1222.75, funds.get(0));
            checkFund(fc2,"Student loans", 2400.00, funds.get(1));
            checkCost(cc1,"Rent", 850, costs.get(0));
            checkCost(cc3,"Tuition", 3050, costs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
