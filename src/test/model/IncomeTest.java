package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IncomeTest {
    private final FundCategory fc1 = FundCategory.EMPLOYMENT;
    private final FundCategory fc2 = FundCategory.LOAN;
    private final FundCategory fc3 = FundCategory.GIFT;
    private final FundCategory fc4 = FundCategory.OTHER;
    private Income myIncome;

    @Test
    public void testAddFund() {
        Income myIncome = new Income();  //empty collection
        List<String> noDescriptions = myIncome.getAllFundDescriptions();
        assertEquals(0, noDescriptions.size());

        myIncome.addFund(fc1,"Work", 2000);
        myIncome.addFund(fc2,"Bursary",1200);

        List<String> descriptions = myIncome.getAllFundDescriptions();
        assertEquals(2, descriptions.size());
    }

    @Test
    public void testGetAllFundDescriptionsEmpty() {
        Income myIncome = new Income();  //empty collection
        List<String> descriptions = myIncome.getAllFundDescriptions();
        assertEquals(0, descriptions.size());
    }

    @Test
    public void testGetAllFundDescriptionsSomeFunds() {
        Income myIncome = new Income();  //empty collection
        List<String> noDescriptions = myIncome.getAllFundDescriptions();
        assertEquals(0, noDescriptions.size());

        myIncome.addFund(fc1,"Work", 2000);
        myIncome.addFund(fc2,"Bursary",1200);

        List<String> descriptions = myIncome.getAllFundDescriptions();
        assertEquals(2, descriptions.size());
    }

    @Test
    public void testGetAllFundDescriptionsManyFunds() {
        Income myIncome = new Income();  //empty collection
        List<String> noDescriptions = myIncome.getAllFundDescriptions();
        assertEquals(0, noDescriptions.size());

        for (int i = 0; i < 10000; i++) {
            myIncome.addFund(fc3,"fund", 200);
        }

        List<String> descriptions = myIncome.getAllFundDescriptions();
        assertEquals(10000, descriptions.size());
    }



    @Test
    public void testGetTotalIncomeEmpty() {
        Income myIncome = new Income();  //empty collection
        assertEquals(0, myIncome.getTotalIncome());
    }

    @Test
    public void testGetTotalIncomeEmptySomeFunds() {
        Income myIncome = new Income();  //empty collection

        myIncome.addFund(fc1,"Work", 2000);
        myIncome.addFund(fc4,"Bursary",1200);
        myIncome.addFund(fc4,"Misc",25.50);

        assertEquals(2000 + 1200 + 25.50, myIncome.getTotalIncome());
    }

    @Test
    public void testGetTotalIncomeManyFunds() {
        Income myIncome = new Income();  //empty collection
        double count = 0;

        for (int i = 0; i < 10000; i++) {
            myIncome.addFund(fc3,"fund", 5);
            count = count + 5;
            assertEquals(count, myIncome.getTotalIncome());
        }
    }
}
