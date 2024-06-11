
//PC1126
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mps.eurocopa.Equipo;
import org.mps.eurocopa.Eurocopa;
import org.mps.eurocopa.EurocopaException;

import static org.assertj.core.api.Assertions.*;

public class EquipoTest {

    private Equipo equipo;

    @BeforeEach
    public void setUp(){
        equipo = new Equipo("Malaga");
    }

    @Nested
    @DisplayName("Tests del método addPuntos")
    class addPuntosTest{

        @Test
        @DisplayName("Test del método addPuntos cuando recibe puntos no válidos")
        public void testAddPuntosException(){

            int puntos = 2;
            assertThatThrownBy(() -> equipo.addPuntos(puntos))
                    .isInstanceOf(EurocopaException.class)
                    .hasMessage("Puntos no validos");
                    
        }

        @Test
        @DisplayName("Test del método addPuntos cuando recibe puntos válidos")
        public void testAddPuntosValid(){
            int puntosE = Eurocopa.PUNTOS_EMPATE; //1 punto
            int puntosG = Eurocopa.PUNTOS_GANAR; //3 puntos

            equipo.addPuntos(puntosE);
            equipo.addPuntos(puntosG);

            assertThat(equipo.getPuntos()).isEqualTo(4);
        }
    }

    @Nested
    @DisplayName("Test de funciones básicas")
    class basicsTest{

        @Test
        @DisplayName("Test del método get")
        public void testGet(){
            int puntosG = Eurocopa.PUNTOS_GANAR;

            equipo.addPuntos(puntosG);

            assertThat(equipo.getPuntos()).isEqualTo(3);
        }

        @Test
        @DisplayName("Test del método equals cuando son iguales")
        public void testEqualsSameObject(){
            Equipo equipo1 = new Equipo("Malaga");
            Equipo equipo2 = new Equipo("Cordoba");
            

            assertThat(equipo.equals(equipo1)).isTrue();
            assertThat(equipo.equals(equipo2)).isFalse();
        }

        @Test
        @DisplayName("Test del método equals cuando son iguales")
        public void testEqualsDifferentObject(){
            Eurocopa euro = new Eurocopa();

            assertThat(equipo.equals(euro)).isFalse();
        }

        @Test
        @DisplayName("Test del método toString")
        public void testToString(){
            int puntosG = Eurocopa.PUNTOS_GANAR;

            equipo.addPuntos(puntosG);
            String expected = equipo.toString();

            assertThat(expected).hasToString("Malaga(3)");
        }

        @Test
        @DisplayName("Test del método compareTo")
        public void testCompareTo(){
            Equipo equipo1 = new Equipo("Malaga");
            Equipo equipo2 = new Equipo("Cordoba");
            Equipo equipo3 = new Equipo("Zaragoza");

            assertThat(equipo.compareTo(equipo1)).isZero();
            assertThat(equipo.compareTo(equipo2)).isPositive();
            assertThat(equipo.compareTo(equipo3)).isNegative();
        }

    }
    
    
}