package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    private Transaction work;
    private Category fc1 = Category.EMPLOYMENT;

    @BeforeEach
    public void setUp() {
        work = new Fund(fc1,"part-time job",250.00 );
    }

    @Test
    public void testConstructor() {
        assertEquals(fc1, work.getCategory());
        assertEquals("part-time job", work.getDescription());
        assertEquals(250.00, work.getAmount());
    }

    @Test
    public void testToJson() {
        JSONObject json = work.toJson();
        String description = json.getString("description");
        double amount = json.getDouble("amount");

        assertEquals("part-time job", description);
        assertEquals(250.00, amount);
    }

}
