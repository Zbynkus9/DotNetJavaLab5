package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProblemTest {

    @Test
    public void testAtLeastOneItemFits() {
        Problem problem = new Problem(5, 1, 1, 10);
        Result result = problem.Solve(20);
        assertFalse(result.itemsInKnapsack.isEmpty());
    }


    @Test
    public void testNoItemFits() {
        Problem problem = new Problem(5, 1, 5, 10);
        Result result = problem.Solve(2);
        assertTrue(result.itemsInKnapsack.isEmpty());
    }

    @Test
    public void testItemsWithinBounds() {
        int lowerBound = 1;
        int upperBound = 10;
        Problem problem = new Problem(50, 1, lowerBound, upperBound);

        for (Item item : problem.items) {
            assertTrue(item.weight >= lowerBound && item.weight <= upperBound);
            assertTrue(item.value >= lowerBound && item.value <= upperBound);
        }
    }

    @Test
    public void testSpecificInstanceResult() {
        Problem problem = new Problem(10, 1, 1, 10);
        Result result = problem.Solve(15);

        assertEquals(15, result.totalWeight);
        assertEquals(33, result.totalValue);
    }
}