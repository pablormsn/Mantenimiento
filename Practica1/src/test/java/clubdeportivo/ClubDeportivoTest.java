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
}
