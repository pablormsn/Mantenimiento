import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import es.uma.informatica.mps.Calendario;

public class CalendarioTest {

    @Test
    @DisplayName("Prueba para año bisiesto")
    @Tag("1")
    public void testDiaSemana_AñoBisiesto() {
        // Año bisiesto (2024), febrero 29 días
        assertEquals(java.time.DayOfWeek.THURSDAY, Calendario.diaSemana(29, 2, 2024));
    }

    @Test
    @DisplayName("Prueba para año no bisiesto")
    @Tag("2")
    public void testDiaSemana_AñoNoBisiesto() {
        // Año no bisiesto (2023), febrero 28 días
        assertEquals(java.time.DayOfWeek.TUESDAY, Calendario.diaSemana(28, 2, 2023));
    }

    @Test
    @DisplayName("Prueba para mes válido")
    @Tag("3")
    public void testDiaSemana_MesValido() {
        assertEquals(java.time.DayOfWeek.TUESDAY, Calendario.diaSemana(23, 4, 2024));
    }

    @Test
    @DisplayName("Prueba para día válido en año no bisiesto")
    @Tag("4")
    public void testDiaSemana_DiaValido_NoBisiesto() {
        assertEquals(java.time.DayOfWeek.SATURDAY, Calendario.diaSemana(15, 4, 2023));
    }

    @Test
    @DisplayName("Prueba para día válido en año bisiesto")
    @Tag("5")
    public void testDiaSemana_DiaValido_Bisiesto() {
        assertEquals(java.time.DayOfWeek.FRIDAY, Calendario.diaSemana(19, 4, 2024));
    }

    // Pruebas para clases de equivalencia inválidas

    @Test
    @DisplayName("Prueba para año negativo")
    @Tag("6")
    public void testDiaSemana_AñoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(1, 1, -2021);
        });
    }

    @Test
    @DisplayName("Prueba para año fuera de rango inferior (año 4 d.C.)")
    @Tag("7")
    public void testDiaSemana_AñoInferior() {
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(27, 2, 3);
        });
    }

    @Test
    @DisplayName("Prueba para mes fuera de rango inferior")
    @Tag("8")
    public void testDiaSemana_MesFueraRangoInferior() {
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(1, 0, 2021);
        });
    }

    @Test
    @DisplayName("Prueba para mes fuera de rango superior")
    @Tag("9")
    public void testDiaSemana_MesFueraRangoSuperior() {
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(1, 13, 2021);
        });
    }

    @Test
    @DisplayName("Prueba para día fuera de rango inferior")
    @Tag("10")
    public void testDiaSemana_DiaFueraRangoInferior() {
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(0, 1, 2021);
        });
    }

    @Test
    @DisplayName("Prueba para día fuera de rango superior en año no bisiesto")
    @Tag("11")
    public void testDiaSemana_DiaFueraRangoSuperior_NoBisiesto() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Febrero en año no bisiesto (2021) tiene 28 días
            Calendario.diaSemana(29, 2, 2021);
        });
    }

    @Test
    @DisplayName("Prueba para día fuera de rango superior en año bisiesto")
    @Tag("12")
    public void testDiaSemana_DiaFueraRangoSuperior_Bisiesto() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Febrero en año bisiesto (2024) tiene 29 días
            Calendario.diaSemana(30, 2, 2024);
        });
    }

    @Test
    @DisplayName("Prueba para día fuera de rango superior en mes de 31 días")
    @Tag("13")
    public void testDiaSemana_DiaFueraRangoSuperior_31Dias() {
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(32, 1, 2021);
        });
    }

    @Test
    @DisplayName("Prueba para día fuera de rango superior en mes de 30 días")
    @Tag("14")
    public void testDiaSemana_DiaFueraRangoSuperior_30Dias() {
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(31, 4, 2021);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14})
    @DisplayName("Prueba para dias inexistentes por cambio de calendario")
    @Tag("15")
    public void testDiaSemana_DiasInexistentes(int dia) {
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(dia, 10, 1582);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1700, 1800, 2100, 2200, 2300})
    @DisplayName("Prueba para años centenarios no bisiestos en Gregoriano")
    @Tag("16")
    public void testDiaSemana_CentenariosNoBisiestos(int anyo){
        assertThrows(IllegalArgumentException.class, () -> {
            Calendario.diaSemana(29, 2, anyo);
        });
    }
}

