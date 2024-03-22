package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClubDeportivoAltoRendimientoTest {
    private ClubDeportivoAltoRendimiento club;

    @BeforeEach
    void setUp() throws ClubException {
        club = new ClubDeportivoAltoRendimiento("Club Test", 5, 1.5);
    }
    
    @Test
    void testConstructor() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", -1, 1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 0, 1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 5, -1.5));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("Club Test", 5, 0));
    }

    @Test
    void testConstructorConTam() {
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

    
}