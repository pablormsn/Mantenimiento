package clubdeportivo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GrupoTest {
    private Grupo grupo;

    @BeforeEach
    public void setUp() {
        try {
            grupo = new Grupo("001", "Yoga", 20, 5, 50.0);
        } catch (ClubException e) {
            fail("Error al inicializar el grupo: " + e.getMessage());
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("001", grupo.getCodigo());
        assertEquals("Yoga", grupo.getActividad());
        assertEquals(20, grupo.getPlazas());
        assertEquals(5, grupo.getMatriculados());
        assertEquals(50.0, grupo.getTarifa(), 0.001);
    }

    @Test
    public void testPlazasLibres() {
        assertEquals(15, grupo.plazasLibres());
    }

    @Test
    public void testActualizarPlazas() {
        try {
            grupo.actualizarPlazas(25);
            assertEquals(25, grupo.getPlazas());
        } catch (ClubException e) {
            fail("Error al actualizar las plazas: " + e.getMessage());
        }
    }

    @Test
    public void testMatricular() {
        try {
            grupo.matricular(3);
            assertEquals(8, grupo.getMatriculados());
        } catch (ClubException e) {
            fail("Error al matricular: " + e.getMessage());
        }
    }

    @Test
    public void testEquals() throws ClubException {
        assertTrue(grupo.equals(new Grupo("001", "Yoga", 20, 5, 50.0)));
        assertFalse(grupo.equals(new Grupo("002", "Yoga", 20, 5, 50.0)));
        assertFalse(grupo.equals(new Grupo("001", "Pilates", 20, 5, 50.0)));
    }

    @Test
    public void testHashCode() throws ClubException {
        assertEquals(grupo.hashCode(), new Grupo("001", "Yoga", 20, 5, 50.0).hashCode());
    }


    @Test
    public void testConstructorException() {
        try {
           grupo = new Grupo("002", "Natación", -10, 5, 50.0);
            fail("Se esperaba una excepción por datos inválidos.");
        } catch (ClubException e) {
            // Esperado
        }
    }

    @Test
    public void testActualizarPlazasException() {
        try {
            grupo.actualizarPlazas(-5);
            fail("Se esperaba una excepción por datos inválidos.");
        } catch (ClubException e) {
            // Esperado
        }
    }

    @Test
    public void testMatricularException() {
        try {
            grupo.matricular(30);
            fail("Se esperaba una excepción por falta de plazas.");
        } catch (ClubException e) {
            // Esperado
        }
    }
}
