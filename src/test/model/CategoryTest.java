package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    private Category cat1 = Category.BILLS;
    private Category defaultCat = Category.OTHER;

    @Test
    void testGetCatStringCase1() {
        assertEquals("Bills", Category.getCatString(cat1));
    }

    @Test
    void testGetCatStringDefaultCase() {
        assertEquals("Other", Category.getCatString(defaultCat));
    }
}
