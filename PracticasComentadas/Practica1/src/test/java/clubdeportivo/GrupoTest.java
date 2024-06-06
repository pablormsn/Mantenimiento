/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */
package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GrupoTest {
    private Grupo grupo;

    // Inicializamos el objeto Grupo, dándole valores válidos para utilizarlo en los tests
    @BeforeEach
    public void initTests() throws ClubException {
        grupo = new Grupo("001", "Yoga", 20, 5, 50.0);

    }

    /*
     * Test para el constructor de la clase Grupo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos, en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos crear un grupo con valores inválidos.
     */
    @Test
    public void testConstructor() { 
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", -10, 5, 50.0)); // nplazas negativo
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 10, -10, 50.0)); // nmatriculados negativo
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 10, 5, -50.0)); // tarifa negativa
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 0, 5, 50.0)); // nplazas 0
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 10, 5, 0)); // tarifa 0
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 10, 15, 50.0)); // nmatriculados > nplazas
    }

    /*
     * Método para testear el método *plazasLibres* de la clase Grupo
     * Comprobamos que el método *plazasLibres* devuelve el número de plazas libres correctamente.
     * Para ello, comprobamos que el número de plazas libres es el esperado, en este caso, 15.
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
     */
    @Test
    public void testPlazasLibres() {
        assertEquals(15, grupo.plazasLibres()); // 20 plazas - 5 matriculados = 15 plazas libres. El método plazasLibres() debería devolver 15, porque resta el número de plazas matriculadas al total de plazas.
    }

    /*
     * Método para testear el método *actualizarPlazas* de la clase Grupo
     * Comprobamos que el método *actualizarPlazas* actualiza el número de plazas correctamente.
     * Para ello, comprobamos que el número de plazas es el esperado, en este caso, 25.
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
    */
    @Test
    public void testActualizarPlazas() throws ClubException {
        grupo.actualizarPlazas(25); // Actualizamos el número de plazas a 25. El método actualizarPlazas() debería actualizar el número de plazas a 25.
        assertEquals(25, grupo.getPlazas()); // Comprobamos que el número de plazas es 25.
    }
    /*
     * Método para testear el método *matricular* de la clase Grupo
     * Comprobamos que el método *matricular* matricula a un número de personas correctamente.
     * Para ello, comprobamos que el número de matriculados es el esperado, en este caso, 8.
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
     */
    @Test
    public void testMatricular() throws ClubException {
        grupo.matricular(3); // Matriculamos a 3 personas. El método matricular() debería sumar 3 al número de matriculados.
        assertEquals(8, grupo.getMatriculados()); // Comprobamos que el número de matriculados es 8.
    }

    /*
     * Método para testear el método *equals* de la clase Grupo
     * Comprobamos que el método *equals* compara correctamente dos objetos de la clase Grupo.
     * Para ello, comprobamos que dos grupos iguales son iguales, y que dos grupos distintos no lo son.
     * Para ello, usamos el método *assertTrue*, que comprueba que un valor es verdadero, y el método *assertFalse*, que comprueba que un valor es falso.
     */
    @Test
    public void testEquals() throws ClubException {
        assertTrue(grupo.equals(new Grupo("001", "Yoga", 20, 5, 50.0))); // Comprobamos que dos grupos iguales son iguales.
        assertFalse(grupo.equals(new Grupo("002", "Yoga", 20, 5, 50.0))); // Comprobamos que dos grupos con distinto código no son iguales.
        assertFalse(grupo.equals(new Grupo("001", "Pilates", 20, 5, 50.0))); // Comprobamos que dos grupos con distinta actividad no son iguales.
        assertFalse(grupo.equals("Esto no es un grupo")); // Comprobamos que un grupo no es igual a un objeto que no es un grupo.
    }

    /*
     * Método para testear el método *toString* de la clase Grupo
     * Comprobamos que el método *toString* devuelve la representación en cadena de un objeto Grupo correctamente.
     * Para ello, comprobamos que la cadena devuelta es la esperada, en este caso, "(001 - Yoga - 50.0 euros - P:20 - M:5)".
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
     */
    @Test
    public void testToString() {
        assertEquals("(001 - Yoga - 50.0 euros - P:20 - M:5)", grupo.toString()); // Comprobamos que la cadena devuelta es la esperada.
    }
    
    /*
     * Método para testear el método *hashCode* de la clase Grupo
     * Comprobamos que el método *hashCode* devuelve el código hash de un objeto Grupo correctamente.
     * Para ello, comprobamos que el código hash devuelto es el esperado, en este caso, el código hash de un grupo con los mismos valores.
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
     */
    @Test
    public void testHashCode() throws ClubException {
        assertEquals(grupo.hashCode(), new Grupo("001", "Yoga", 20, 5, 50.0).hashCode()); // Comprobamos que el código hash devuelto es el esperado.
    }


    /*
     * Test para los errores del constructor de la clase Grupo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos, en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos crear un grupo con valores inválidos.
     * Además, usamos un try-catch para capturar la excepción y que no falle el test. El método *fail* hace que el test falle si se ejecuta, proporcionando un mensaje de error.
     */
    @Test
    public void testConstructorException() {
        try {
           grupo = new Grupo("002", "Natación", -10, 5, 50.0); 
            fail("Se esperaba una excepción por datos inválidos.");
        } catch (ClubException e) {
            // Esperado
        }
    }
    /*
     * Es una buena práctica de testing separar las pruebas de comportamiento normal y las pruebas de manejo de errores en diferentes métodos de test. 
     * Esto hace que las pruebas sean más claras y fáciles de entender, y también permite que fallen de manera independiente, 
     * lo que puede facilitar la depuración cuando algo sale mal.
     * Probaremos los métodos actualizarPlazas y matricular con valores inválidos.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos actualizar el número de plazas o matricular a un número de personas con valores inválidos.
     */
    @Test
    public void testActualizarPlazasException() {
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(-5)); // n negativo
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(4)); // n < nmatriculados
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(0)); // n = 0
            
    }
    /*
     * Probaremos el método matricular con valores inválidos.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos matricular a un número de personas con valores inválidos.
     */
    @Test
    public void testMatricularException() {
        assertThrows(ClubException.class, () -> grupo.matricular(-10)); // n negativo
        assertThrows(ClubException.class, () -> grupo.matricular(30)); // n > plazas libres
        assertThrows(ClubException.class, () -> grupo.matricular(0)); // n = 0
        
    }

    /* 
    @Test
    void testGetCodigo() {
        assertEquals("001", grupo.getCodigo());
    }
    */
}
