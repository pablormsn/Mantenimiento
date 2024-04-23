package org.mps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mps.crossover.CrossoverOperator;
import org.mps.crossover.OnePointCrossover;
import org.mps.mutation.MutationOperator;
import org.mps.mutation.SwapMutation;
import org.mps.selection.SelectionOperator;
import org.mps.selection.TournamentSelection;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;


public class EvolutionaryAlgorithmTest {
    SelectionOperator selectionOperator;
    MutationOperator mutationOperator;
    CrossoverOperator crossoverOperator;
    EvolutionaryAlgorithm ea;

    @BeforeEach
    public void setUp() throws Exception {
        selectionOperator = new TournamentSelection(2);
        mutationOperator = new SwapMutation();
        crossoverOperator = new OnePointCrossover();
        ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
    }

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTest{

        @Test
        public void testConstructorWithNullOperators() throws EvolutionaryAlgorithmException {
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                new EvolutionaryAlgorithm(null, null, null);
            });
        }

        @Test
        public void testConstructorWithValidOperators() throws EvolutionaryAlgorithmException {
            assertNotNull(ea);
        }  

        @Test
        public void testConstructorWithNullSelectionOperator() throws EvolutionaryAlgorithmException {
            SelectionOperator selectionOperator = null;
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
            });
        }

        @Test
        public void testConstructorWithNullMutationOperator() throws EvolutionaryAlgorithmException {
            MutationOperator mutationOperator = null;
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
            });
        }

        @Test
        public void testConstructorWithNullCrossoverOperator() throws EvolutionaryAlgorithmException {
            CrossoverOperator crossoverOperator = null;
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
            });
        }
        
    }
    
    
    @Nested
    @DisplayName("Optimize tests")
    class OptimizeTest{

        @Test
        public void testOptimizeWithNullPopulation() throws EvolutionaryAlgorithmException {
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                ea.optimize(null);
            });
        }

        @Test
        public void testOptimizeWithEmptyPopulation() throws EvolutionaryAlgorithmException {
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                ea.optimize(new int[0][]);
            });
        }

        @Test
        public void testOptimizeWithValidPopulation() throws EvolutionaryAlgorithmException {
            int[][] population = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
            int[][] result = ea.optimize(population);
            assertNotNull(result);
        }
    }
    
    @Nested
    @DisplayName("CrossoverOperator tests")
    class CrossoverOperatorTest{
        @Test
        public void testGetCrossoverOperator() throws EvolutionaryAlgorithmException {
            CrossoverOperator obtainedCrossoverOperator = ea.getCrossoverOperator();
    
            // Verificamos si el operador de cruce devuelto es el mismo que el establecido anteriormente
            assertEquals(crossoverOperator, obtainedCrossoverOperator);
        }
    
        @Test
        public void testSetCrossoverOperator() throws EvolutionaryAlgorithmException {
            // Operador de selección diferente
            CrossoverOperator crossoverOperator2 = new OnePointCrossover();
            ea.setCrossoverOperator(crossoverOperator2);
    
            // Verificamos si el operador de selección establecido es el mismo que el obtenido mediante getSelectionOperator
            CrossoverOperator obtainedCrossoverOperator = ea.getCrossoverOperator();
            assertEquals(crossoverOperator2, obtainedCrossoverOperator);
        }
    }

    @Nested
    @DisplayName("SelectionOperator tests")
    class SelectionOperatorTest{
        @Test
        public void testGetSelectionOperator() throws EvolutionaryAlgorithmException {
            // Creamos operadores de selección
            SelectionOperator obtainedSelectionOperator = ea.getSelectionOperator();
    
            // Verificamos si el operador de selección devuelto es el mismo que el establecido anteriormente
            assertEquals(selectionOperator, obtainedSelectionOperator);
        }
    
        @Test
        public void testSetSelectionOperator() throws EvolutionaryAlgorithmException {
            // Operador de selección diferente
            SelectionOperator selectionOperator2 = new TournamentSelection(2); 
            ea.setSelectionOperator(selectionOperator2);
    
            // Verificamos si el operador de selección establecido es el mismo que el obtenido mediante getSelectionOperator
            SelectionOperator obtainedSelectionOperator = ea.getSelectionOperator();
            assertEquals(selectionOperator2, obtainedSelectionOperator);
        }

        @Test
        public void testSelectWithNullPopulation() throws EvolutionaryAlgorithmException {
            SelectionOperator selectionOperator = new TournamentSelection(2);
            int[] population = null;
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                selectionOperator.select(population);
            });
        }

        @Test
        public void testSelectWithEmptyPopulation() throws EvolutionaryAlgorithmException {
            SelectionOperator selectionOperator = new TournamentSelection(2);
            int[] population = {};
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                selectionOperator.select(population);
            });
        }

        @Test
        public void testTournamentSelectionNegativeSize() throws EvolutionaryAlgorithmException {
            assertThrows(EvolutionaryAlgorithmException.class, () ->  {
                new TournamentSelection(-1);
            });
        }
       
    
    }

    @Nested
    @DisplayName("MutationOperator tests")
    class MutationOperatorTest{
        @Test
        public void testMutateEmptyIndividual() throws EvolutionaryAlgorithmException {
            MutationOperator mutationOperator = new SwapMutation();
            int[] individual = {};
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                mutationOperator.mutate(individual);
            });
        }
    
        // Prueba para cubrir el caso cuando individual es nulo o tiene longitud cero
        @Test
        public void testMutateWithNullIndividual() throws EvolutionaryAlgorithmException {
            MutationOperator mutationOperator = new SwapMutation();
            int[] individual = null;
            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                mutationOperator.mutate(individual);
            });
        }

        @Test
        public void testGetMutationOperator() throws EvolutionaryAlgorithmException {
            MutationOperator mutationOperator2 = ea.getMutationOperator();
    
            // Verificamos si el operador de mutación devuelto es el mismo que el establecido anteriormente
            assertEquals(mutationOperator, mutationOperator2);
        }
    
        @Test
        public void testSetMutationOperator() throws EvolutionaryAlgorithmException {
    
            MutationOperator mutationOperator2 = new SwapMutation(); // Operador de mutación diferente
    
            ea.setMutationOperator(mutationOperator2);
    
            // Verificamos si el operador de mutación establecido es el mismo que el obtenido mediante getMutationOperator
            MutationOperator obtainedMutationOperator = ea.getMutationOperator();
            assertEquals(mutationOperator2, obtainedMutationOperator);
        }
    }
}
