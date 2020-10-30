package model;

import org.junit.jupiter.api.BeforeEach;

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