/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */
package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClubDeportivoAltoRendimientoTest {
    private ClubDeportivoAltoRendimiento club; //Creamos un objeto de la clase ClubDeportivoAltoRendimiento

    /*
     * Inicializamos el objeto ClubDeportivoAltoRendimiento, dándole valores válidos para utilizarlo en los tests
     * Para ello, usamos el método *BeforeEach*, que se ejecuta antes de cada test.
     */
    @BeforeEach
    void initTests() throws ClubException {
        club = new ClubDeportivoAltoRendimiento("Club Test", 5, 1.5); //Inicializamos el objeto ClubDeportivoAltoRendimiento con los valores "Club Test", 5 y 1.5.
    }

    /*
     * Test para el constructor de la clase ClubDeportivoAltoRendimiento
     * Comprobamos que se ha creado el objeto correctamente, comprobando que el nombre del club es el esperado.
     * Para ello, usamos el método *assertTrue*, que comprueba que una condición es verdadera.
     */
    @Test
    void testConstructor() throws ClubException {
        club = new ClubDeportivoAltoRendimiento("Club Test",10, 5, 1.5); //Inicializamos el objeto ClubDeportivoAltoRendimiento con los valores "Club Test", 10, 5 y 1.5.
        assertTrue(club.toString().contains("Club Test")); //Comprobamos que el nombre del club es "Club Test".
    }
    
    /*
     * Test para el constructor de la clase ClubDeportivoAltoRendimiento
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos, en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos crear un club con valores inválidos.
     */
    @Test
    void testConstructorException() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", -1, 1.5)); // maximoPersonasGrupo negativo
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 0, 1.5)); // maximoPersonasGrupo 0
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 5, -1.5)); // incremento negativo
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 5, 0)); // incremento 0
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test",10, -1, 1.5)); // maximoPersonasGrupo negativo en el segundo constructor
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test",10,  0, 1.5)); // maximoPersonasGrupo 0 en el segundo constructor
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test",10,  5, -1.5)); // incremento negativo en el segundo constructor
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test",10,  5, 0)); // incremento 0 en el segundo constructor
        
    }

    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivoAltoRendimiento
     * Comprobamos que se añaden las actividades correctamente al club.
     * Para ello, comprobamos que el club contiene las actividades añadidas.
     * Para ello, usamos el método *assertTrue*, que comprueba que una condición es verdadera.
     */
    @Test
    void testAnyadirActividad() throws ClubException {
        String[] datos = {"123A", "Kizomba", "5", "5", "25.0"}; //Creamos un array de Strings con los datos de la actividad
        club.anyadirActividad(datos); //Añadimos la actividad al club
        Grupo grupo = new Grupo("123A", "Kizomba", 5, 5, 25.0); //Creamos un objeto Grupo con los datos de la actividad
        assertTrue(club.toString().contains(grupo.toString())); //Comprobamos que el club contiene la actividad añadida

        String[] datos2 = {"123B", "Correr", "10", "5", "25.0"}; //Creamos un array de Strings con los datos de la actividad
        club.anyadirActividad(datos2); //Añadimos la actividad al club
        Grupo grupo2 = new Grupo("123B", "Correr", 5, 5, 25.0); //Creamos un objeto Grupo con los datos de la actividad
        assertTrue(club.toString().contains(grupo2.toString())); //Comprobamos que el club contiene la actividad añadida

    }
    
    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivoAltoRendimiento
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos, en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos añadir una actividad con valores inválidos.
     */
    @Test
    void testAnyadirActividadDatosIncorrectos() {
        String[] datos = {"Grupo1", "Actividad1", "diez", "5", "100.0"}; //Creamos un array de Strings con los datos de la actividad
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos)); //Comprobamos que se lanza una excepción al añadir una actividad con datos incorrectos
        
    }

    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivoAltoRendimiento
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos, en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos añadir una actividad con datos faltantes.
     */
    @Test
    void testAnyadirActividadDatosFaltantes() {
        String[] datos = {"Grupo1", "Actividad1", "10", "5"}; //Creamos un array de Strings con los datos de la actividad, pero faltan datos
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos)); //Comprobamos que se lanza una excepción al añadir una actividad con datos faltantes
    }

    /*
     * Test para el método *ingresos* de la clase ClubDeportivoAltoRendimiento
     * Comprobamos que los ingresos del club son los esperados.
     * Para ello, comprobamos que los ingresos son los esperados, en este caso, 750.0.
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
     */
    @Test
    void testIngresos() {
        // Setup: Create activities and add them to the club
        String[] datos1 = {"Grupo1", "Actividad1", "5", "5", "100.0"}; //Creamos un array de Strings con los datos de la actividad
        String[] datos2 = {"Grupo2", "Actividad2", "5", "5", "200.0"}; //Creamos un array de Strings con los datos de la actividad
        try { //Añadimos las actividades al club y comprobamos que se añaden correctamente
            club.anyadirActividad(datos1);
            club.anyadirActividad(datos2);
        } catch (ClubException e) {
            fail("Adding activity failed: " + e.getMessage()); //Si falla, mostramos un mensaje de error
        }
    
        // Execute: Call the method under test
        double ingresos = club.ingresos(); //Calculamos los ingresos del club
    
        // Verify: Check that the result is as expected
        // The expected income is the sum of the income of all activities plus the increment
        double expectedIncome = (5 * 100.0 + 5 * 200.0) + (5 * 100.0 + 5 * 200.0) * (1.5 / 100); //Calculamos los ingresos esperados
        assertEquals(expectedIncome, ingresos); //Comprobamos que los ingresos son los esperados
    }
    
}