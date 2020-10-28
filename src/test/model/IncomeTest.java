//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class IncomeTest {
//    private Income myIncome;
//
//    @Test
//    public void testAddFund() {
//        Income myIncome = new Income();  //empty collection
//        List<String> noDescriptions = myIncome.getAllFundDescriptions();
//        assertEquals(0, noDescriptions.size());
//
//        myIncome.addFund("Work", 2000);
//        myIncome.addFund("Bursary",1200);
//
//        List<String> descriptions = myIncome.getAllFundDescriptions();
//        assertEquals(2, descriptions.size());
//    }
//
//    @Test
//    public void testGetAllFundDescriptionsEmpty() {
//        Income myIncome = new Income();  //empty collection
//        List<String> descriptions = myIncome.getAllFundDescriptions();
//        assertEquals(0, descriptions.size());
//    }
//
//    @Test
//    public void testGetAllFundDescriptionsSomeFunds() {
//        Income myIncome = new Income();  //empty collection
//        List<String> noDescriptions = myIncome.getAllFundDescriptions();
//        assertEquals(0, noDescriptions.size());
//
//        myIncome.addFund("Work", 2000);
//        myIncome.addFund("Bursary",1200);
//
//        List<String> descriptions = myIncome.getAllFundDescriptions();
//        assertEquals(2, descriptions.size());
//    }
//
//    @Test
//    public void testGetAllFundDescriptionsManyFunds() {
//        Income myIncome = new Income();  //empty collection
//        List<String> noDescriptions = myIncome.getAllFundDescriptions();
//        assertEquals(0, noDescriptions.size());
//
//        for (int i = 0; i < 10000; i++) {
//            myIncome.addFund("fund", 200);
//        }
//
//        List<String> descriptions = myIncome.getAllFundDescriptions();
//        assertEquals(10000, descriptions.size());
//    }
//
//
//
//    @Test
//    public void testGetTotalIncomeEmpty() {
//        Income myIncome = new Income();  //empty collection
//        assertEquals(0, myIncome.getTotalIncome());
//    }
//
//    @Test
//    public void testGetTotalIncomeEmptySomeFunds() {
//        Income myIncome = new Income();  //empty collection
//
//        myIncome.addFund("Work", 2000);
//        myIncome.addFund("Bursary",1200);
//        myIncome.addFund("Misc",25.50);
//
//        assertEquals(2000 + 1200 + 25.50, myIncome.getTotalIncome());
//    }
//
//    @Test
//    public void testGetTotalIncomeManyFunds() {
//        Income myIncome = new Income();  //empty collection
//        double count = 0;
//
//        for (int i = 0; i < 10000; i++) {
//            myIncome.addFund("fund", 5);
//            count = count + 5;
//            assertEquals(count, myIncome.getTotalIncome());
//        }
//    }
//}
