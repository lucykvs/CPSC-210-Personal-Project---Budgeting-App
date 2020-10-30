package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetTest {
    private Budget budget;
    private final CostCategory cc1 = CostCategory.BILLS;
    private final FundCategory fc1 = FundCategory.EMPLOYMENT;
    private final FundCategory fc4 = FundCategory.OTHER;

    @BeforeEach
    public void setUp() {
        budget = new Budget();
    }
}