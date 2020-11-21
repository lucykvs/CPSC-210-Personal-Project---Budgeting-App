package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    private Transaction work;
    private Category fc1 = Category.EMPLOYMENT;

    @BeforeEach
    void setUp() {
        work = new Fund(fc1,"part-time job",250.00 );
    }

    @Test
    void testConstructor() {
        assertEquals(fc1, work.getCategory());
        assertEquals("part-time job", work.getDescription());
        assertEquals(250.00, work.getAmount());
    }

    @Test
    void testToJson() {
        JSONObject json = work.toJson();
        String description = json.getString("description");
        double amount = json.getDouble("amount");

        assertEquals("part-time job", description);
        assertEquals(250.00, amount);
    }

    @Test
    void testHashCode() {
        Transaction transaction1 = new Cost(Category.BILLS, "Rent", 800);
        Transaction transaction2 = new Cost(Category.MISCELLANEOUS_PURCHASES, "Rent", 800);
        Transaction transaction3 = new Cost(Category.BILLS, "Rent", 800.0);

        assertEquals(transaction1.hashCode(), transaction3.hashCode());
        assertNotEquals(transaction1.hashCode(), transaction2.hashCode());
    }

    @Test
    void testEquals() {
        Transaction transaction1 = new Cost(Category.BILLS, "Rent", 800);
        Transaction transaction2 = new Cost(Category.MISCELLANEOUS_PURCHASES, "Rent", 800);
        Transaction transaction3 = new Cost(Category.BILLS, "Rent", 800.0);
        Transaction transaction4 = new Cost(Category.BILLS, "Tent", 800.0);
        Transaction transaction5 = new Cost(Category.BILLS, "Tent", 850.0);
        Transaction transactionNull = null;
        Category c = Category.GIFT;

        assertEquals(transaction1, transaction1);
        assertNotEquals(transactionNull, transaction1);
        assertFalse(transaction1.equals(null));
        assertFalse(transaction1.equals(c));
        assertNotEquals(transaction2, transaction1);
        assertEquals(transaction3, transaction1);
        assertNotEquals(transaction4, transaction1);
        assertNotEquals(transaction5, transaction1);
    }
}
