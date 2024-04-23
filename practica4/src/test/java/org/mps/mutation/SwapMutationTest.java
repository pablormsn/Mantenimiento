package org.mps.mutation;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mps.EvolutionaryAlgorithmException;

public class SwapMutationTest {
    private SwapMutation swapMutation;

    @BeforeEach
    public void setUp() {
        swapMutation = new SwapMutation();
    }

    @Test
    public void testMutate() throws EvolutionaryAlgorithmException{
        int[] individual = new int[]{1, 2, 3, 4, 5};
        int[] mutatedIndividual = swapMutation.mutate(individual);
        assertNotEquals(individual, mutatedIndividual);
        assertEquals(individual.length, mutatedIndividual.length);
    }

    @Test
    public void testMutateWithEmptyIndividual() {
        int[] individual = new int[]{};
        assertThrows(EvolutionaryAlgorithmException.class, () -> swapMutation.mutate(individual));
    }

    @Test
    public void testMutateWithNullIndividual() {
        assertThrows(EvolutionaryAlgorithmException.class, () -> swapMutation.mutate(null));
    }

    @Test
    public void testMutateWithSingleElementIndividual() throws EvolutionaryAlgorithmException {
        int[] individual = new int[]{1};
        int[] mutatedIndividual = swapMutation.mutate(individual);
        assertArrayEquals(individual, mutatedIndividual);
    }

    @Test
    public void testMutateWithLargeArray() throws EvolutionaryAlgorithmException {
        int[] individual = new int[1000];
        for (int i = 0; i < individual.length; i++) {
            individual[i] = i;
        }
        int[] mutatedIndividual = swapMutation.mutate(individual);
        assertNotEquals(individual, mutatedIndividual);
        assertEquals(individual.length, mutatedIndividual.length);
    }
    
}
