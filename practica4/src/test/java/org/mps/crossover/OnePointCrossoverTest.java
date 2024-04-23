package org.mps.crossover;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OnePointCrossoverTest {

    @Test
    void testCrossover() {
        OnePointCrossover crossover = new OnePointCrossover();
        Individual parent1 = new Individual("1111");
        Individual parent2 = new Individual("0000");

        Individual[] offspring = crossover.crossover(parent1, parent2);

        assertNotEquals(parent1, offspring[0]);
        assertNotEquals(parent2, offspring[1]);

        assertTrue(offspring[0].getGenes().contains("1"));
        assertTrue(offspring[0].getGenes().contains("0"));
        assertTrue(offspring[1].getGenes().contains("1"));
        assertTrue(offspring[1].getGenes().contains("0"));
    }
}