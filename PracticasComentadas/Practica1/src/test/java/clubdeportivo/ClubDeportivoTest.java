/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */
package clubdeportivo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    private ClubDeportivo club; // Objeto ClubDeportivo
    private ClubDeportivo club2; // Objeto ClubDeportivo

    /*
     * Inicializamos el objeto ClubDeportivo, dándole valores válidos para utilizarlo en los tests
     * Para ello, usamos el método *initTests*, que se ejecuta antes de cada test.
     * Usaremos los dos constructores de la clase ClubDeportivo, para inicializar cada objeto
     * @BeforeEach: indica que el método se ejecutará antes de cada test.
     */
    @BeforeEach
    public void initTests() throws ClubException {
        club = new ClubDeportivo("Club Deportivo"); // Inicializamos el objeto ClubDeportivo con el constructor que recibe solo el nombre del club
        club2 = new ClubDeportivo("Club Deportivo", 1); // Inicializamos el objeto ClubDeportivo con el constructor que recibe el nombre del club y el número de grupos
    }

    /*
     * Test para el constructor de la clase ClubDeportivo
     * Vamos a comprobar que se han inicializado correctamente los atributos del objeto ClubDeportivo
     * Para ello, comprobamos que el nombre del club es el esperado, en este caso, "Club Deportivo".
     * Para ello, usamos el método *assertTrue*, que comprueba que una condición es verdadera.
     */
    @Test
    public void testClubDeportivo() {
        String cadena = club.toString(); // Obtenemos el nombre del club y lo guardamos en la variable cadena
        assertTrue(cadena.contains("Club Deportivo")); // Comprobamos que la cadena contiene "Club Deportivo"
    }

    /*
     * Test para el constructor de la clase ClubDeportivo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos, en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos crear un club con valores inválidos.
     */
    @Test
    void testConstructor() {
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club Test", -1)); // Número de grupos negativo
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club Test", 0)); // Número de grupos 0
    }

    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivo
     * En este test, vamos a probar el método que recibe un array de Strings con los datos de la actividad
     * Comprobamos que se añade una actividad correctamente al club
     * Para ello, comprobamos que el grupo se ha añadido al club correctamente
     * Para ello, usamos el método *assertTrue*, que comprueba que una condición es verdadera.
     */
    @Test
    public void testAnyadirActividadDatos() throws ClubException {
        String[] datos = {"123A", "Kizomba", "10", "10", "25.0"}; // Datos de la actividad, que se guardan en un array de Strings
        club.anyadirActividad(datos); // Añadimos la actividad al club
        Grupo grupo = new Grupo("123A", "Kizomba", 10, 10, 25.0); // Creamos un objeto Grupo con los datos de la actividad
        assertTrue(club.toString().contains(grupo.toString())); // Comprobamos que el grupo se ha añadido al club, comparando las cadenas de texto
    }

    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos, en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos añadir una actividad con valores inválidos.
     */
    @Test
    void testAnyadirActividadDatosIncorrectos() {
        String[] datos = {"Grupo1", "Actividad1", "diez", "5", "100.0"}; // Datos de la actividad, que se guardan en un array de Strings. El número de plazas es incorrecto, porque no es un número.
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos)); // Comprobamos que se lanza una excepción, porque el número de plazas no es un número
    }

    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivo
     * Esta vez, vamos a probar el método que recibe un objeto Grupo con los datos de la actividad
     * Comprobamos que se añade una actividad correctamente al club
     * Para ello, comprobamos que el grupo se ha añadido al club correctamente
     * Para ello, usamos el método *assertTrue*, que comprueba que una condición es verdadera.
     */
    @Test
    void testAnyadirActividad() throws ClubException {
        Grupo g = new Grupo("Grupo1", "Actividad1", 10, 5, 100.0); // Creamos un objeto Grupo con los datos de la actividad
        club.anyadirActividad(g); // Añadimos la actividad al club
        assertTrue(club.toString().contains(g.toString())); // Comprobamos que el grupo se ha añadido al club, comparando las cadenas de texto
    }

    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos (null), en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos añadir una actividad con valores inválidos.
     */
    @Test
    void testAnyadirActividadNull() {
        assertThrows(ClubException.class, () -> club.anyadirActividad((Grupo) null)); // Comprobamos que se lanza una excepción, porque el grupo es nulo
    }

    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivo
     * Comprobamos que dos grupos son iguales
     * Para ello, usamos el método *assertTrue*, que comprueba que una condición es verdadera.
     */
    @Test
    void testAnyadirActividadGrupoExistente() throws ClubException {
        Grupo g1 = new Grupo("Grupo1", "Actividad1", 10, 5, 100.0); // Creamos un objeto Grupo con los datos de la actividad
        Grupo g2 = new Grupo("Grupo1", "Actividad1", 20, 5, 100.0); // Creamos un objeto Grupo con los datos de la actividad. El grupo es igual al anterior, pero con más plazas, cosa que no influye en la igualdad de los grupos
        club.anyadirActividad(g1); // Añadimos la actividad al club
        club.anyadirActividad(g2); // Añadimos la actividad al club
        assertTrue(club.toString().contains(g2.toString()) && g1.toString().equals(g2.toString())); // Comprobamos que el grupo se ha añadido al club, comparando las cadenas de texto
        //Lo ideal sería comprobar que las plazas se han actualizado correctamente, pero no se puede acceder a los atributos de la clase Grupo porque son privados y no hay getters.
    }

    /*
     * Test para el método *anyadirActividad* de la clase ClubDeportivo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se supera el límite de grupos, en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos añadir una actividad y se supera el límite de grupos.
     */
    @Test
    void testAnyadirActividadLimiteDeGrupos() throws ClubException {
        Grupo g1 = new Grupo("Grupo1", "Actividad1", 10, 5, 100.0); // Creamos un objeto Grupo con los datos de la actividad
        Grupo g2 = new Grupo("Grupo2", "Actividad2", 20, 5, 100.0); // Creamos un objeto Grupo con los datos de la actividad, distinto al anterior
        club2.anyadirActividad(g1); // Añadimos la actividad al club 2, el cual tiene un límite de 1 grupo
        assertThrows(ClubException.class, () -> club2.anyadirActividad(g2)); // Comprobamos que se lanza una excepción, porque se supera el límite de grupos
    }


    /*
     * Test para el método *ingresos* de la clase ClubDeportivo
     * Comprobamos que el método *ingresos* devuelve la cantidad de dinero que ha ingresado el club
     * Para ello, comprobamos que la cantidad de dinero es la esperada, en este caso, 2500.0
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
     */
    @Test
    void testIngresos() throws ClubException {
        String[] datos1 = {"Grupo1", "Actividad1", "10", "5", "100.0"}; // Datos de la actividad, que se guardan en un array de Strings. Los ingresos de la actividad son 500.0 (5 matriculados * 100.0 tarifa)
        String[] datos2 = {"Grupo2", "Actividad2", "20", "10", "200.0"}; // Datos de la actividad, que se guardan en un array de Strings. Los ingresos de la actividad son 2000.0 (10 matriculados * 200.0 tarifa)
        
        club.anyadirActividad(datos1); // Añadimos la actividad al club
        club.anyadirActividad(datos2); // Añadimos la actividad al club

        double ingresos = club.ingresos(); // Obtenemos los ingresos del club

        assertEquals(2500.0, ingresos); // Comprobamos que los ingresos son 2500.0
}


    /*
     * Test para el método *plazasLibres* de la clase ClubDeportivo
     * Comprobamos que el método *plazasLibres* devuelve el número de plazas libres de una actividad
     * Para ello, comprobamos que el número de plazas libres es el esperado, en este caso, 5
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
     */
    @Test
    void testPlazasLibres() throws ClubException {
        String[] datos1 = {"Grupo1", "Actividad1", "10", "5", "100.0"}; // Datos de la actividad, que se guardan en un array de Strings
        String[] datos2 = {"Grupo2", "Actividad2", "20", "10", "200.0"}; // Datos de la actividad, que se guardan en un array de Strings
        
        club.anyadirActividad(datos1); // Añadimos la actividad al club
        club.anyadirActividad(datos2); // Añadimos la actividad al club

        int plazasLibres = club.plazasLibres("Actividad1"); // Obtenemos las plazas libres de la actividad "Actividad1". Debido a que hay 5 matriculados y 10 plazas, hay 5 plazas libres
        int plazasLibres2 = club.plazasLibres("Actividad2"); // Obtenemos las plazas libres de la actividad "Actividad2". Debido a que hay 10 matriculados y 20 plazas, hay 10 plazas libres

        assertEquals(5, plazasLibres); // Comprobamos que las plazas libres son 5
        assertEquals(10, plazasLibres2); // Comprobamos que las plazas libres son 10
    }

    /*
     * Test para el método *plazasLibres* de la clase ClubDeportivo
     * Comprobamos que el método *plazasLibres* devuelve 0 cuando la actividad no existe
     * Para ello, comprobamos que el número de plazas libres es 0
     * Para ello, usamos el método *assertEquals*, que comprueba que dos valores son iguales.
     */
    @Test
    void testPlazasLibresActividadInexistente() throws ClubException {
        String[] datos1 = {"Grupo1", "Actividad1", "10", "5", "100.0"}; // Datos de la actividad, que se guardan en un array de Strings
        String[] datos2 = {"Grupo2", "Actividad2", "20", "10", "200.0"}; // Datos de la actividad, que se guardan en un array de Strings
        
        club.anyadirActividad(datos1); // Añadimos la actividad al club
        club.anyadirActividad(datos2); // Añadimos la actividad al club

        int plazasLibres = club.plazasLibres("Actividad3"); // Obtenemos las plazas libres de la actividad "Actividad3". Debido a que la actividad no existe, el número de plazas libres es 0

        assertEquals(0, plazasLibres); // Comprobamos que las plazas libres son 0
    }

    /*
     * Test para el método *matricular* de la clase ClubDeportivo
     * Comprobamos que el método *matricular* matricula a un número de personas correctamente en un club
     */
    @Test
    void testMatricular() throws ClubException {
        String[] datos1 = {"Grupo1", "Actividad1", "10", "5", "100.0"}; // Datos de la actividad, que se guardan en un array de Strings
        String[] datos2 = {"Grupo2", "Actividad2", "20", "10", "200.0"}; // Datos de la actividad, que se guardan en un array de Strings
        
        club.anyadirActividad(datos1); // Añadimos la actividad al club
        club.anyadirActividad(datos2); // Añadimos la actividad al club

        club.matricular("Actividad1", 3); // Matriculamos a 3 personas en la actividad "Actividad1"
        club.matricular("Actividad2", 5); // Matriculamos a 5 personas en la actividad "Actividad2"

        assertEquals(2, club.plazasLibres("Actividad1")); // Comprobamos que las plazas libres de la actividad "Actividad1" son 2, puesto que había 5 plazas libres y se han matriculado 3 personas
        assertEquals(5, club.plazasLibres("Actividad2")); // Comprobamos que las plazas libres de la actividad "Actividad2" son 5, puesto que había 10 plazas libres y se han matriculado 5 personas
    }

    /*
     * Test para el método *matricular* de la clase ClubDeportivo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos (actividad inexistente), en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos matricular en una actividad que no existe.
     */
    @Test
    void testMatricularActividadInexistente() throws ClubException {
        String[] datos1 = {"Grupo1", "Actividad1", "10", "5", "100.0"}; // Datos de la actividad, que se guardan en un array de Strings
        String[] datos2 = {"Grupo2", "Actividad2", "20", "10", "200.0"}; // Datos de la actividad, que se guardan en un array de Strings
        
        club.anyadirActividad(datos1);  // Añadimos la actividad al club
        club.anyadirActividad(datos2); // Añadimos la actividad al club

        assertThrows(ClubException.class, () -> club.matricular("Actividad3", 3)); // Comprobamos que se lanza una excepción, porque la actividad no existe
    }

    /*
     * Test para el método *matricular* de la clase ClubDeportivo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos (0), en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos matricular en una actividad y no hay plazas suficientes.
     */
    @Test
    void testMatricularCeroPersonas() throws ClubException {
        String[] datos1 = {"Grupo1", "Actividad1", "10", "5", "100.0"}; // Datos de la actividad, que se guardan en un array de Strings
        String[] datos2 = {"Grupo2", "Actividad2", "20", "10", "200.0"}; // Datos de la actividad, que se guardan en un array de Strings
        
        club.anyadirActividad(datos1); // Añadimos la actividad al club
        club.anyadirActividad(datos2); // Añadimos la actividad al club
        club.matricular("Actividad1", 0); // Matriculamos a 0 personas en la actividad "Actividad1"
        assertEquals(5, club.plazasLibres("Actividad1")); // Comprobamos que las plazas libres de la actividad "Actividad1" son 5, puesto que no se ha matriculado a nadie
    }

    /*
     * Test para el método *matricular* de la clase ClubDeportivo
     * Comprobamos que se lanzan las excepciones correspondientes cuando se introducen valores inválidos (queremos matricular más personas de las posibles), en este caso, ClubException.
     * Para ello, usamos el método *assertThrows*, que comprueba que se lanza una excepción de un tipo determinado, en este caso, ClubException, cuando queremos matricular en una actividad y no hay plazas suficientes.
     */
    @Test
    void testMatricularPlazasInsuficientes () throws ClubException {
        String[] datos1 = {"Grupo1", "Actividad1", "10", "5", "100.0"}; // Datos de la actividad, que se guardan en un array de Strings
        String[] datos2 = {"Grupo2", "Actividad2", "20", "10", "200.0"}; // Datos de la actividad, que se guardan en un array de Strings
        
        club.anyadirActividad(datos1); // Añadimos la actividad al club
        club.anyadirActividad(datos2); // Añadimos la actividad al club
        assertThrows(ClubException.class, () -> club.matricular("Actividad1", 6)); // Comprobamos que se lanza una excepción, porque no hay plazas libres suficientes
    }

}
