package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import ui.BudgetApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FundTest {
    private Fund work;
    private FundCategory fc1 = FundCategory.EMPLOYMENT;

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


//    @Override
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("category", category);
//        json.put("description", description);
//        json.put("amount", amount);
//        return json;
//    }


    @Test
    public void testToJson() {
        JSONObject json = work.toJson();
        // String category = json.toString();
        String description = json.getString("description");
        double amount = json.getDouble("amount");

        assertEquals("part-time job", description);
        assertEquals(250.00, amount);
    }

}
