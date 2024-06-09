/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */

package org.mps;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

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


/*
 * Clase de pruebas unitarias para la clase EvolutionaryAlgorithm
 * Como atributos, crearemos un objeto de cada tipo de operador y un objeto de la clase EvolutionaryAlgorithm.
 * Es necesario crear un objeto de cada operador porque la clase EvolutionaryAlgorithm no tiene un constructor sin parámetros.
 */
public class EvolutionaryAlgorithmTest {
    SelectionOperator selectionOperator; // Operador de selección
    MutationOperator mutationOperator; // Operador de mutación
    CrossoverOperator crossoverOperator; // Operador de cruce
    EvolutionaryAlgorithm ea; // Algoritmo evolutivo

    /*
     * Método que se ejecuta antes de cada prueba unitaria.
     * En este método, inicializamos los atributos de la clase.
     */
    @BeforeEach
    public void setUp() throws Exception {
        selectionOperator = new TournamentSelection(2); // Operador de selección. Torneo de tamaño 2
        mutationOperator = new SwapMutation(); // Operador de mutación. Intercambio
        crossoverOperator = new OnePointCrossover(); // Operador de cruce. Un punto
        ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator); // Creamos un objeto de la clase EvolutionaryAlgorithm
    } 

    /*
     * Clase anidada para agrupar las pruebas unitarias de los test del constructor de la clase EvolutionaryAlgorithm
     * Se han creado varios métodos de prueba para comprobar el comportamiento del constructor de la clase EvolutionaryAlgorithm.
     * @Nested permite agrupar las pruebas unitarias de una misma clase.
     * @DisplayName permite establecer un nombre personalizado para la clase anidada.
     */
    @Nested
    @DisplayName("Constructor tests") // Nombre personalizado para la clase anidada
    class ConstructorTest{

        /*
         * Prueba unitaria para comprobar el comportamiento del constructor de la clase EvolutionaryAlgorithm cuando se le pasan operadores nulos.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testConstructorWithNullOperators() throws EvolutionaryAlgorithmException {
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción. También podría escribirse como: () -> new EvolutionaryAlgorithm(null, null, null);
                new EvolutionaryAlgorithm(null, null, null); // Se crea un objeto de la clase EvolutionaryAlgorithm con operadores nulos
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del constructor de la clase EvolutionaryAlgorithm cuando se le pasan operadores válidos.
         * Se espera que no se lance ninguna excepción y que el objeto se cree correctamente.
         * Para ello, se utiliza el método assertNotNull, que espera que el objeto no sea nulo.
         */
        @Test
        public void testConstructorWithValidOperators() throws EvolutionaryAlgorithmException {
            assertNotNull(ea); // Se espera que el objeto no sea nulo. Recordemos que ea es un objeto de la clase EvolutionaryAlgorithm, creado en el método setUp e inicializado con operadores válidos.
        }  

        /*
         * Prueba unitaria para comprobar el comportamiento del constructor de la clase EvolutionaryAlgorithm cuando se le pasa un operador de selección nulo.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testConstructorWithNullSelectionOperator() throws EvolutionaryAlgorithmException {
            SelectionOperator selectionOperator = null; // Operador de selección nulo
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción. También podría escribirse como: () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
                new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator); // Se crea un objeto de la clase EvolutionaryAlgorithm con un operador de selección nulo
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del constructor de la clase EvolutionaryAlgorithm cuando se le pasa un operador de mutación nulo.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testConstructorWithNullMutationOperator() throws EvolutionaryAlgorithmException {
            MutationOperator mutationOperator = null; // Operador de mutación nulo
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción. También podría escribirse como: () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
                new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator); // Se crea un objeto de la clase EvolutionaryAlgorithm con un operador de mutación nulo
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del constructor de la clase EvolutionaryAlgorithm cuando se le pasa un operador de cruce nulo.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testConstructorWithNullCrossoverOperator() throws EvolutionaryAlgorithmException {
            CrossoverOperator crossoverOperator = null; // Operador de cruce nulo
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción. También podría escribirse como: () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
                new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator); // Se crea un objeto de la clase EvolutionaryAlgorithm con un operador de cruce nulo
            });
        }
        
    }
    
    /*
     * Clase anidada para agrupar las pruebas unitarias de los test del método optimize de la clase EvolutionaryAlgorithm
     * Se han creado varios métodos de prueba para comprobar el comportamiento del método optimize de la clase EvolutionaryAlgorithm.
     * @Nested permite agrupar las pruebas unitarias de una misma clase.
     * @DisplayName permite establecer un nombre personalizado para la clase anidada.
     */
    @Nested
    @DisplayName("Optimize tests")
    class OptimizeTest{

        /*
         * Prueba unitaria para comprobar el comportamiento del método optimize de la clase EvolutionaryAlgorithm cuando se le pasa una población nula.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testOptimizeWithNullPopulation() throws EvolutionaryAlgorithmException {
            assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(null)); // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
        }

        @Test
        public void testOptimizeWithPopulationWithEmptyIndividuals() throws EvolutionaryAlgorithmException {
            int[][] population = {{}, {}, {}}; // Población con individuos vacíos
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                ea.optimize(population); // Se llama al método optimize de la clase EvolutionaryAlgorithm con una población con individuos vacíos
            });
        }

        @Test 
        public void testOptimizeWithPopulationWithNullIndividuals() throws EvolutionaryAlgorithmException {
            int[][] population = {null, null, null}; // Población con individuos nulos
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                ea.optimize(population); // Se llama al método optimize de la clase EvolutionaryAlgorithm con una población con individuos nulos
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del método optimize de la clase EvolutionaryAlgorithm cuando se le pasa una población válida.
         * Se espera que no se lance ninguna excepción y que el método devuelva un resultado no nulo.
         * Para ello, se utiliza el método assertNotNull, que espera que el objeto no sea nulo.
         */
        @Test
        public void testOptimizeWithValidPopulation() throws EvolutionaryAlgorithmException {
            int[][] population = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}}; // Población válida
            int[][] result = ea.optimize(population); // Se llama al método optimize de la clase EvolutionaryAlgorithm con una población válida
            assertNotNull(result); // Se espera que el resultado no sea nulo
        }
    }
    
    /*
     * Clase anidada para agrupar las pruebas unitarias de los test de los operadores de cruce de la clase EvolutionaryAlgorithm
     * Se han creado varios métodos de prueba para comprobar el comportamiento de los operadores de cruce de la clase EvolutionaryAlgorithm.
     * @Nested permite agrupar las pruebas unitarias de una misma clase.
     * @DisplayName permite establecer un nombre personalizado para la clase anidada.
     */
    @Nested
    @DisplayName("CrossoverOperator tests")
    class CrossoverOperatorTest{

        /*
         * Prueba unitaria para comprobar el comportamiento del método getCrossoverOperator de la clase EvolutionaryAlgorithm.
         * Se espera que el método devuelva el operador de cruce establecido anteriormente.
         * Para ello, se utiliza el método assertEquals, que compara dos objetos y verifica si son iguales.
         */
        @Test
        public void testGetCrossoverOperator() throws EvolutionaryAlgorithmException {
            //Arrange (nada)

            //Act
            CrossoverOperator obtainedCrossoverOperator = ea.getCrossoverOperator(); // Obtenemos el operador de cruce
    
            //Assert
            // Verificamos si el operador de cruce devuelto es el mismo que el establecido anteriormente
            assertEquals(crossoverOperator, obtainedCrossoverOperator); // Comparamos el operador de cruce establecido con el obtenido
        }
    
        /*
         * Prueba unitaria para comprobar el comportamiento del método setCrossoverOperator de la clase EvolutionaryAlgorithm.
         * Se espera que el método establezca el operador de cruce correctamente.
         * Para ello, se utiliza el método assertEquals, que compara dos objetos y verifica si son iguales.
         */
        @Test
        public void testSetCrossoverOperator() throws EvolutionaryAlgorithmException {
            // Operador de selección diferente
            CrossoverOperator crossoverOperator2 = new OnePointCrossover(); // Creamos un nuevo operador de cruce
            ea.setCrossoverOperator(crossoverOperator2); // Establecemos el nuevo operador de cruce
    
            // Verificamos si el operador de selección establecido es el mismo que el obtenido mediante getSelectionOperator
            CrossoverOperator obtainedCrossoverOperator = ea.getCrossoverOperator(); // Obtenemos el operador de cruce
            assertEquals(crossoverOperator2, obtainedCrossoverOperator); // Comparamos el operador de cruce establecido con el obtenido
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del método crossover de la clase OnePointCrossover cuando se le pasan padres nulos.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testCrossoverWithNullParents() throws EvolutionaryAlgorithmException {
            CrossoverOperator crossoverOperator = new OnePointCrossover(); // Creamos un operador de cruce de un punto
            int[] parent1 = null; // Padre 1 nulo
            int[] parent2 = null; // Padre 2 nulo
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                crossoverOperator.crossover(parent1, parent2); // Se llama al método crossover del operador de cruce con padres nulos
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del método crossover de la clase OnePointCrossover cuando se le pasan padres vacíos.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testCrossoverWithEmptyParents() throws EvolutionaryAlgorithmException {
            CrossoverOperator crossoverOperator = new OnePointCrossover(); // Creamos un operador de cruce de un punto
            int[] parent1 = {}; // Padre 1 vacío
            int[] parent2 = {}; // Padre 2 vacío
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                crossoverOperator.crossover(parent1, parent2); // Se llama al método crossover del operador de cruce con padres vacíos
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del método crossover de la clase OnePointCrossover cuando se le pasan padres de diferente longitud.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testCrossoverWithParentsWithDiffLenght() throws EvolutionaryAlgorithmException {
            CrossoverOperator crossoverOperator = new OnePointCrossover(); // Creamos un operador de cruce de un punto
            int[] parent1 = {1}; // Padre 1 con longitud 1
            int[] parent2 = {1,2}; // Padre 2 con longitud 2
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                crossoverOperator.crossover(parent1, parent2); // Se llama al método crossover del operador de cruce con padres de diferente longitud
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del método crossover de la clase OnePointCrossover cuando se le pasa un padre nulo.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testCrossoverWithParent2Null() throws EvolutionaryAlgorithmException {
            CrossoverOperator crossoverOperator = new OnePointCrossover(); // Creamos un operador de cruce de un punto
            int[] parent1 = {1}; // Padre 1 con longitud 1
            int[] parent2 = null; // Padre 2 nulo
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                crossoverOperator.crossover(parent1, parent2); // Se llama al método crossover del operador de cruce con un padre nulo
            });
        }

    }

    /*
     * Clase anidada para agrupar las pruebas unitarias de los test de los operadores de selección de la clase EvolutionaryAlgorithm
     * Se han creado varios métodos de prueba para comprobar el comportamiento de los operadores de selección de la clase EvolutionaryAlgorithm.
     * @Nested permite agrupar las pruebas unitarias de una misma clase.
     * @DisplayName permite establecer un nombre personalizado para la clase anidada.
     */
    @Nested
    @DisplayName("SelectionOperator tests") // Nombre personalizado para la clase anidada
    class SelectionOperatorTest{

        /*
         * Prueba unitaria para comprobar el comportamiento del método getSelectionOperator de la clase EvolutionaryAlgorithm.
         * Se espera que el método devuelva el operador de selección establecido anteriormente.
         * Para ello, se utiliza el método assertEquals, que compara dos objetos y verifica si son iguales.
         */
        @Test
        public void testGetSelectionOperator() throws EvolutionaryAlgorithmException {
            // Creamos operadores de selección
            SelectionOperator obtainedSelectionOperator = ea.getSelectionOperator(); // Obtenemos el operador de selección
    
            // Verificamos si el operador de selección devuelto es el mismo que el establecido anteriormente
            assertEquals(selectionOperator, obtainedSelectionOperator); // Comparamos el operador de selección establecido con el obtenido
        }
    
        /*
         * Prueba unitaria para comprobar el comportamiento del método setSelectionOperator de la clase EvolutionaryAlgorithm.
         * Se espera que el método establezca el operador de selección correctamente.
         * Para ello, se utiliza el método assertEquals, que compara dos objetos y verifica si son iguales.
         */
        @Test
        public void testSetSelectionOperator() throws EvolutionaryAlgorithmException {
            // Operador de selección diferente
            SelectionOperator selectionOperator2 = new TournamentSelection(2);  // Creamos un nuevo operador de selección
            ea.setSelectionOperator(selectionOperator2); // Establecemos el nuevo operador de selección
    
            // Verificamos si el operador de selección establecido es el mismo que el obtenido mediante getSelectionOperator
            SelectionOperator obtainedSelectionOperator = ea.getSelectionOperator(); // Obtenemos el operador de selección
            assertEquals(selectionOperator2, obtainedSelectionOperator); // Comparamos el operador de selección establecido con el obtenido
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del método select de la clase TournamentSelection cuando se le pasa una población nula.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testSelectWithNullPopulation() throws EvolutionaryAlgorithmException {
            SelectionOperator selectionOperator = new TournamentSelection(2); // Creamos un operador de selección por torneo de tamaño 2
            int[] population = null; // Población nula
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                selectionOperator.select(population); // Se llama al método select del operador de selección con una población nula
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del método select de la clase TournamentSelection cuando se le pasa una población vacía.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testSelectWithEmptyPopulation() throws EvolutionaryAlgorithmException {
            SelectionOperator selectionOperator = new TournamentSelection(2); // Creamos un operador de selección por torneo de tamaño 2
            int[] population = {}; // Población vacía
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                selectionOperator.select(population); // Se llama al método select del operador de selección con una población vacía
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del constructor de la clase TournamentSelection cuando se le pasa un tamaño de torneo negativo.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testTournamentSelectionNegativeSize() throws EvolutionaryAlgorithmException {
            assertThrows(EvolutionaryAlgorithmException.class, () ->  { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                new TournamentSelection(-1); // Se crea un objeto de la clase TournamentSelection con un tamaño de torneo negativo
            });
        }
    }

    /*
     * Clase anidada para agrupar las pruebas unitarias de los test de los operadores de mutuación de la clase EvolutionaryAlgorithm
     * Se han creado varios métodos de prueba para comprobar el comportamiento de los operadores de mutación de la clase EvolutionaryAlgorithm.
     * @Nested permite agrupar las pruebas unitarias de una misma clase.
     * @DisplayName permite establecer un nombre personalizado para la clase anidada.
     */
    @Nested
    @DisplayName("MutationOperator tests") // Nombre personalizado para la clase anidada
    class MutationOperatorTest{

        /*
         * Prueba unitaria para comprobar el comportamiento del método mutate de la clase SwapMutation cuando se le pasa un individuo vacío.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testMutateEmptyIndividual() throws EvolutionaryAlgorithmException {
            MutationOperator mutationOperator = new SwapMutation(); // Creamos un operador de mutación de intercambio
            int[] individual = {}; // Individuo vacío
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                mutationOperator.mutate(individual); // Se llama al método mutate del operador de mutación con un individuo vacío
            });
        }
    
        /*
         * Prueba unitaria para comprobar el comportamiento del método mutate de la clase SwapMutation cuando se le pasa un individuo nulo.
         * Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException.
         * Para ello, se utiliza el método assertThrows, que espera que se lance una excepción de un tipo concreto.
         */
        @Test
        public void testMutateWithNullIndividual() throws EvolutionaryAlgorithmException {
            MutationOperator mutationOperator = new SwapMutation(); // Creamos un operador de mutación de intercambio
            int[] individual = null; // Individuo nulo
            assertThrows(EvolutionaryAlgorithmException.class, () -> { // Se espera que se lance una excepción de tipo EvolutionaryAlgorithmException. El {} contiene el código que se espera que lance la excepción.
                mutationOperator.mutate(individual); // Se llama al método mutate del operador de mutación con un individuo nulo
            });
        }

        /*
         * Prueba unitaria para comprobar el comportamiento del método getMutationOperator de la clase EvolutionaryAlgorithm.
         * Se espera que el método devuelva el operador de mutación establecido anteriormente.
         * Para ello, se utiliza el método assertEquals, que compara dos objetos y verifica si son iguales.
         */
        @Test
        public void testGetMutationOperator() throws EvolutionaryAlgorithmException {
            MutationOperator mutationOperator2 = ea.getMutationOperator(); // Obtenemos el operador de mutación
    
            // Verificamos si el operador de mutación devuelto es el mismo que el establecido anteriormente
            assertEquals(mutationOperator, mutationOperator2); // Comparamos el operador de mutación establecido con el obtenido
        }
    
        /*
         * Prueba unitaria para comprobar el comportamiento del método setMutationOperator de la clase EvolutionaryAlgorithm.
         * Se espera que el método establezca el operador de mutación correctamente.
         * Para ello, se utiliza el método assertEquals, que compara dos objetos y verifica si son iguales.
         */
        @Test
        public void testSetMutationOperator() throws EvolutionaryAlgorithmException {
    
            MutationOperator mutationOperator2 = new SwapMutation(); // Operador de mutación diferente
    
            ea.setMutationOperator(mutationOperator2); // Establecemos el nuevo operador de mutación
    
            // Verificamos si el operador de mutación establecido es el mismo que el obtenido mediante getMutationOperator
            MutationOperator obtainedMutationOperator = ea.getMutationOperator(); // Obtenemos el operador de mutación
            assertEquals(mutationOperator2, obtainedMutationOperator); // Comparamos el operador de mutación establecido con el obtenido
        }
    }
}
