package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClubDeportivoAltoRendimientoTest {
    private ClubDeportivoAltoRendimiento club;

    @BeforeEach
    void initTests() throws ClubException {
        club = new ClubDeportivoAltoRendimiento("Club Test", 5, 1.5);
    }

    @Test
    void testConstructor() throws ClubException {
        club = new ClubDeportivoAltoRendimiento("Club Test",10, 5, 1.5);
        assertTrue(club.toString().contains("Club Test"));
    }
    
    @Test
    void testConstructorException() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", -1, 1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 0, 1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 5, -1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 5, 0));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test",10, -1, 1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test",10,  0, 1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test",10,  5, -1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test",10,  5, 0));
        
    }

    @Test
    void testAnyadirActividad() throws ClubException {
        String[] datos = {"123A", "Kizomba", "5", "5", "25.0"};
        club.anyadirActividad(datos);
        Grupo grupo = new Grupo("123A", "Kizomba", 5, 5, 25.0);
        assertTrue(club.toString().contains(grupo.toString()));

        String[] datos2 = {"123B", "Correr", "10", "5", "25.0"};
        club.anyadirActividad(datos2);
        Grupo grupo2 = new Grupo("123B", "Correr", 5, 5, 25.0);
        assertTrue(club.toString().contains(grupo2.toString()));

    }
    
    @Test
    void testAnyadirActividadDatosIncorrectos() {
        String[] datos = {"Grupo1", "Actividad1", "diez", "5", "100.0"};
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
        
    }

    @Test
    void testAnyadirActividadDatosFaltantes() {
        String[] datos = {"Grupo1", "Actividad1", "10", "5"};
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    void testIngresos() {
        // Setup: Create activities and add them to the club
        String[] datos1 = {"Grupo1", "Actividad1", "5", "5", "100.0"};
        String[] datos2 = {"Grupo2", "Actividad2", "5", "5", "200.0"};
        try {
            club.anyadirActividad(datos1);
            club.anyadirActividad(datos2);
        } catch (ClubException e) {
            fail("Adding activity failed: " + e.getMessage());
        }
    
        // Execute: Call the method under test
        double ingresos = club.ingresos();
    
        // Verify: Check that the result is as expected
        // The expected income is the sum of the income of all activities plus the increment
        double expectedIncome = (5 * 100.0 + 5 * 200.0) + (5 * 100.0 + 5 * 200.0) * (1.5 / 100);
        assertEquals(expectedIncome, ingresos);
    }
    
}