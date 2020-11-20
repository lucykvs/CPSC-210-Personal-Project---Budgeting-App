package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    @Test
    void testGetCatStringCase1() {
        assertEquals("Bills", Category.getCatString(Category.BILLS));
        assertEquals("Other", Category.getCatString(Category.OTHER));
    }

    @Test
    void testValueOfLabel() {
        assertEquals(Category.BILLS, Category.valueOfLabel("Bills"));
        assertEquals(Category.OTHER, Category.valueOfLabel("Other"));
    }
}
