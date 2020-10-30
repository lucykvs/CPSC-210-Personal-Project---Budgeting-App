package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
