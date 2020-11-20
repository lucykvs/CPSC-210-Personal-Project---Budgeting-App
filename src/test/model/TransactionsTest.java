package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionsTest {
    private final Category cc1 = Category.BILLS;
    private final Category cc2 = Category.DEBT_REPAYMENTS;
    private final Category cc3 = Category.ONE_TIME_EXPENSES;
    private final Category cc4 = Category.MISCELLANEOUS_PURCHASES;
    private final Category cc5 = Category.FOR_FUN;
    private User user;
    private Transactions myExpenses;

    @BeforeEach
    void runBefore() {
        user = new User("Lucy");
        myExpenses = new Transactions();
    }

    @Test
    public void testAddTransaction() {
        List<String> noDescriptions = myExpenses.getAllTransactionDescriptions();
        assertEquals(0, noDescriptions.size());

        myExpenses.addTransaction(new Cost(cc4,"Groceries", 150));
        myExpenses.addTransaction(new Cost(cc4,"Gas",90));

        List<String> descriptions = myExpenses.getAllTransactionDescriptions();
        assertEquals(2, descriptions.size());
    }

    @Test
    public void testGetAllTransactionDescriptionsEmpty() {
        List<String> descriptions = myExpenses.getAllTransactionDescriptions();
        assertEquals(0, descriptions.size());
    }

    @Test
    public void testGetAllTransactionDescriptionsSomeTransactions() {
        List<String> noDescriptions = myExpenses.getAllTransactionDescriptions();
        assertEquals(0, noDescriptions.size());

        myExpenses.addTransaction(new Cost(cc4,"Groceries", 150));
        myExpenses.addTransaction(new Cost(cc4,"Gas",90));

        List<String> descriptions = myExpenses.getAllTransactionDescriptions();
        assertEquals(2, descriptions.size());
    }

    @Test
    public void testGetAllTransactionDescriptionsManyTransactions() {
        List<String> noDescriptions = myExpenses.getAllTransactionDescriptions();
        assertEquals(0, noDescriptions.size());

        for (int i = 0; i < 10000; i++) {
            myExpenses.addTransaction(new Cost(cc5,"cost", 200));
        }

        List<String> descriptions = myExpenses.getAllTransactionDescriptions();
        assertEquals(10000, descriptions.size());
    }



    @Test
    public void testGetTotalTransactionsEmpty() {
        assertEquals(0, myExpenses.getTotalTransactions());
    }

    @Test
    public void testGetTotalTransactionsEmptySomeTransactions() {
        myExpenses.addTransaction(new Cost(cc4,"Groceries", 200));
        myExpenses.addTransaction(new Cost(cc4,"Gas",90.50));
        myExpenses.addTransaction(new Cost(cc3,"Tuition",3000));

        assertEquals(200 + 90.50 + 3000, myExpenses.getTotalTransactions());
    }

    @Test
    public void testGetTotalTransactionsManyTransactions() {
        double count = 0;

        for (int i = 0; i < 10000; i++) {
            myExpenses.addTransaction(new Cost(cc5,"cost", 5));
            count = count + 5;
            assertEquals(count, myExpenses.getTotalTransactions());
        }
    }

    @Test
    public void testGetTransactionsEmptyTransactions(){
        List<Transaction> myCosts = myExpenses.getTransactions();

        assertEquals(0,myCosts.size());
    }

    @Test
    public void testGetTransactionsNonEmptyTransactions() {
        myExpenses.addTransaction(new Cost(cc1, "Rent", 850));

        List<Transaction> myCosts = myExpenses.getTransactions();

        assertEquals(1, myCosts.size());
    }

    @Test
    public void testTransactionsToJson() {
        JSONArray jsonArray = new JSONArray();
        myExpenses.addTransaction(new Cost(cc1,"Rent", 850));
        myExpenses.addTransaction(new Cost(cc2,"Student loan payments",200));

        jsonArray = myExpenses.transactionsToJson();
        assertEquals(2,jsonArray.length());
    }
}
