package clubdeportivo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    private ClubDeportivo club;
    private ClubDeportivo club2;

    @BeforeEach
    public void initTests() throws ClubException {
        club = new ClubDeportivo("Club Deportivo");
        club2 = new ClubDeportivo("Club Deportivo", 1);
    }

    @Test
    public void testClubDeportivo() {
        String cadena = club.toString();
        assertTrue(cadena.contains("Club Deportivo"));
    }

    @Test
    void testConstructor() {
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club Test", -1));
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club Test", 0));
    }

    @Test
    public void testAnyadirActividadDatos() throws ClubException {
        String[] datos = {"123A", "Kizomba", "10", "10", "25.0"};
        club.anyadirActividad(datos);
        Grupo grupo = new Grupo("123A", "Kizomba", 10, 10, 25.0);
        assertTrue(club.toString().contains(grupo.toString()));
    }

    @Test
    void testAnyadirActividadDatosIncorrectos() {
        String[] datos = {"Grupo1", "Actividad1", "diez", "5", "100.0"};
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    void testAnyadirActividad() throws ClubException {
        Grupo g = new Grupo("Grupo1", "Actividad1", 10, 5, 100.0);
        club.anyadirActividad(g);
        assertTrue(club.toString().contains(g.toString()));

    }

    @Test
    void testAnyadirActividadGrupoExistente() throws ClubException {
        Grupo g1 = new Grupo("Grupo1", "Actividad1", 10, 5, 100.0);
        Grupo g2 = new Grupo("Grupo1", "Actividad1", 20, 5, 100.0);
        club.anyadirActividad(g1);
        club.anyadirActividad(g2);
        assertTrue(club.toString().contains(g2.toString()) && g1.toString().equals(g2.toString()));        
    }

    @Test
    void testAnyadirActividadLimiteDeGrupos() throws ClubException {
        Grupo g1 = new Grupo("Grupo1", "Actividad1", 10, 5, 100.0);
        Grupo g2 = new Grupo("Grupo2", "Actividad2", 20, 5, 100.0);
        club2.anyadirActividad(g1);
        assertThrows(ClubException.class, () -> club2.anyadirActividad(g2));   
    }

    @Test
    void testAnyadirActividadNull() {
        assertThrows(ClubException.class, () -> club.anyadirActividad((Grupo) null));
    }

    @Test
    void testIngresos() throws ClubException {
        String[] datos1 = {"Grupo1", "Actividad1", "10", "5", "100.0"};
        String[] datos2 = {"Grupo2", "Actividad2", "20", "10", "200.0"};
        
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);

        double ingresos = club.ingresos();

        assertEquals(2500.0, ingresos);
}
}
