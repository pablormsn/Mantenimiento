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

    @BeforeEach
    public void initTests() throws ClubException {
        grupo = new Grupo("001", "Yoga", 20, 5, 50.0);

    }

    @Test
    public void testConstructor() {
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", -10, 5, 50.0));
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 10, -10, 50.0));
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 10, 5, -50.0));
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 0, 5, 50.0));
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 10, 5, 0));
        assertThrows(ClubException.class, () -> new Grupo("002", "Natación", 10, 15, 50.0));
        
        
    }

    @Test
    public void testPlazasLibres() {
        assertEquals(15, grupo.plazasLibres());
    }

    @Test
    public void testActualizarPlazas() throws ClubException {
        grupo.actualizarPlazas(25);
        assertEquals(25, grupo.getPlazas());

    }

    @Test
    public void testMatricular() throws ClubException {
            grupo.matricular(3);
            assertEquals(8, grupo.getMatriculados());

    }

    @Test
    public void testEquals() throws ClubException {
        assertTrue(grupo.equals(new Grupo("001", "Yoga", 20, 5, 50.0)));
        assertFalse(grupo.equals(new Grupo("002", "Yoga", 20, 5, 50.0)));
        assertFalse(grupo.equals(new Grupo("001", "Pilates", 20, 5, 50.0)));
        assertFalse(grupo.equals("Esto no es un grupo"));
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
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(-5));
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(4));
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(0));
            
    }

    @Test
    public void testMatricularException() {
        assertThrows(ClubException.class, () -> grupo.matricular(-10));
        assertThrows(ClubException.class, () -> grupo.matricular(30));
        assertThrows(ClubException.class, () -> grupo.matricular(0));
        
    }

    @Test
    void testGetCodigo() {
        assertEquals("001", grupo.getCodigo());
    }
}