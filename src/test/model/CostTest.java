//package model;
//
//import org.json.JSONObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CostTest {
//    private Cost rent;
//    private CostCategory cc1 = CostCategory.BILLS;
//
//    @BeforeEach
//    public void setUp() {
//        rent = new Cost(cc1,"rent",850.00 );
//    }
//
//    @Test
//    public void testConstructor() {
//        assertEquals(cc1, rent.getCategory());
//        assertEquals("rent", rent.getDescription());
//        assertEquals(850.00, rent.getAmount());
//    }
//
//    @Test
//    public void testToJson() {
//        JSONObject json = rent.toJson();
//        String description = json.getString("description");
//        double amount = json.getDouble("amount");
//
//        assertEquals("rent", description);
//        assertEquals(850.00, amount);
//    }
//}
//
