package clubdeportivo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    private ClubDeportivo club;

    @BeforeEach
    public void initTests() throws ClubException {
        club = new ClubDeportivo("Club Deportivo");
    }

    @Test
    public void testClubDeportivo() {
        String cadena = club.toString();
        assertTrue(cadena.contains("Club Deportivo"));
    }

    @Test
    public void testAnyadirActividad() throws ClubException {
        String[] grupo1 = {"123A", "Kizomba", "10", "10", "25.0"};
        club.anyadirActividad(grupo1);
        Grupo grupo = new Grupo("123A", "Kizomba", 10, 10, 25.0);
        assertTrue(club.toString().contains(grupo.toString()));
    }
}
