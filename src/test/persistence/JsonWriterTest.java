package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    private final Category cc1 = Category.BILLS;
    private final Category cc3 = Category.ONE_TIME_EXPENSES;
    private final Category fc1 = Category.EMPLOYMENT;
    private final Category fc2 = Category.LOAN;

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
            assertEquals(0, user.income.getTransactions().size());
            assertEquals(0, user.expenses.getTransactions().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBudget() {
        try {
            User user = new User("Lucy");
            user.addFund(fc1,"Las Margs", 1222.75);
            user.addFund(fc2, "Student loans", 2400.00);
            user.addCost(cc1, "Rent", 850);
            user.addCost(cc3, "Tuition", 3050);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBudget.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBudget.json");
            user = reader.read();
            assertEquals("Lucy", user.getName());
            List<Transaction> funds = user.getIncome().getTransactions();
            List<Transaction> costs = user.getExpenses().getTransactions();
            assertEquals(2, funds.size());
            assertEquals(2, costs.size());
            checkFund(fc1,"Las Margs", 1222.75, funds.get(0));
            checkFund(fc2,"Student loans", 2400.00, funds.get(1));
            checkCost(cc1,"Rent", 850, costs.get(0));
            checkCost(cc3,"Tuition", 3050, costs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown.");
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException should not have been thrown.");
        }
    }
}
