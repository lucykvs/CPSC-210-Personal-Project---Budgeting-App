//package model;
//
//import org.json.JSONArray;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ExpensesTest {
//    private final CostCategory cc1 = CostCategory.BILLS;
//    private final CostCategory cc2 = CostCategory.DEBT_REPAYMENTS;
//    private final CostCategory cc3 = CostCategory.ONE_TIME_EXPENSES;
//    private final CostCategory cc4 = CostCategory.MISCELLANEOUS_PURCHASES;
//    private final CostCategory cc5 = CostCategory.FOR_FUN;
//
//    @Test
//    public void testAddCost() {
//        Expenses myExpenses = new Expenses();  //empty collection
//        List<String> noDescriptions = myExpenses.getAllCostDescriptions();
//        assertEquals(0, noDescriptions.size());
//
//        myExpenses.addCost(cc4,"Groceries", 150);
//        myExpenses.addCost(cc4,"Gas",90);
//
//        List<String> descriptions = myExpenses.getAllCostDescriptions();
//        assertEquals(2, descriptions.size());
//    }
//
//    @Test
//    public void testGetAllCostDescriptionsEmpty() {
//        Expenses expenses = new Expenses();  //empty collection
//        List<String> descriptions = expenses.getAllCostDescriptions();
//        assertEquals(0, descriptions.size());
//    }
//
//    @Test
//    public void testGetAllCostDescriptionsSomeCosts() {
//        Expenses myExpenses = new Expenses();  //empty collection
//        List<String> noDescriptions = myExpenses.getAllCostDescriptions();
//        assertEquals(0, noDescriptions.size());
//
//        myExpenses.addCost(cc4,"Groceries", 150);
//        myExpenses.addCost(cc4,"Gas",90);
//
//        List<String> descriptions = myExpenses.getAllCostDescriptions();
//        assertEquals(2, descriptions.size());
//    }
//
//    @Test
//    public void testGetAllCostDescriptionsManyCosts() {
//        Expenses myExpenses = new Expenses();  //empty collection
//        List<String> noDescriptions = myExpenses.getAllCostDescriptions();
//        assertEquals(0, noDescriptions.size());
//
//        for (int i = 0; i < 10000; i++) {
//            myExpenses.addCost(cc5,"cost", 200);
//        }
//
//        List<String> descriptions = myExpenses.getAllCostDescriptions();
//        assertEquals(10000, descriptions.size());
//    }
//
//
//
//    @Test
//    public void testGetTotalExpensesEmpty() {
//        Expenses myExpenses = new Expenses();  //empty collection
//        assertEquals(0, myExpenses.getTotalExpenses());
//    }
//
//    @Test
//    public void testGetTotalExpensesEmptySomeCosts() {
//        Expenses myExpenses = new Expenses();  //empty collection
//
//        myExpenses.addCost(cc4,"Groceries", 200);
//        myExpenses.addCost(cc4,"Gas",90.50);
//        myExpenses.addCost(cc3,"Tuition",3000);
//
//        assertEquals(200 + 90.50 + 3000, myExpenses.getTotalExpenses());
//    }
//
//    @Test
//    public void testGetTotalExpensesManyCosts() {
//        Expenses myExpenses = new Expenses();  //empty collection
//        double count = 0;
//
//        for (int i = 0; i < 10000; i++) {
//            myExpenses.addCost(cc5,"cost", 5);
//            count = count + 5;
//            assertEquals(count, myExpenses.getTotalExpenses());
//        }
//    }
//
//    @Test
//    public void testGetCostsEmptyExpenses(){
//        Expenses myExpenses = new Expenses();
//        List<Cost> myCosts = myExpenses.getCosts();
//
//        assertEquals(0,myCosts.size());
//    }
//
//    @Test
//    public void testGetCostsNonEmptyIncome() {
//        Expenses myExpenses = new Expenses();
//        myExpenses.addCost(cc1, "Rent", 850);
//
//        List<Cost> myCosts = myExpenses.getCosts();
//
//        assertEquals(1, myCosts.size());
//    }
//
//    @Test
//    public void testExpensesToJson() {
//        JSONArray jsonArray = new JSONArray();
//        Expenses myExpenses = new Expenses();
//        myExpenses.addCost(cc1,"Rent", 850);
//        myExpenses.addCost(cc2,"Student loan payments",200);
//
//        jsonArray = myExpenses.expensesToJson();
//        assertEquals(2,jsonArray.length());
//    }
//}
