package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    @Test
    void testGetCatStringCase1() {
        assertEquals("Bills", Category.getCatString(Category.BILLS));
        assertEquals( "Debt repayments", Category.getCatString(Category.DEBT_REPAYMENTS));
        assertEquals("One-time expenses", Category.getCatString(Category.ONE_TIME_EXPENSES));
        assertEquals("Miscellaneous purchases", Category.getCatString(Category.MISCELLANEOUS_PURCHASES));
        assertEquals("For fun", Category.getCatString(Category.FOR_FUN));
        assertEquals("Employment", Category.getCatString(Category.EMPLOYMENT));
        assertEquals("Loan", Category.getCatString(Category.LOAN));
        assertEquals("Gift", Category.getCatString(Category.GIFT));
        assertEquals("Other", Category.getCatString(Category.OTHER));
    }
}
