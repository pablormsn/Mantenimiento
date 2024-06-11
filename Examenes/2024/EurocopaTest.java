EurocopaTest.java

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mps.eurocopa.Equipo;
import org.mps.eurocopa.Eurocopa;
import org.mps.eurocopa.EurocopaException;
import org.mps.eurocopa.Resultado;

public class EurocopaTest {

    private Eurocopa euro;

    @Mock
    private Resultado res;

    @BeforeEach
    public void setUp(){
        euro = new Eurocopa();
        res = Mockito.mock(Resultado.class);
    }

@Nested
@DisplayName("Tests del método getNumeroEquipos")
class getNumeroEquiposTest{

    @Test
    @DisplayName("Test que comprueba que cuando no hay grupos, devuelve 0")
    public void testGetNumeroEquiposEmptyGroups(){
        assertThat(euro.getNumeroEquipos()).isZero();
    }

    @Test
    @DisplayName("Test que comprueba que cuando hay un solo grupo, devuelve el numero de equipos que hay en ese grupo")
    public void testGetNumeroEquiposOnlyOneGroup(){
        String lineA = "GrupoA:Alemania,Escocia,Hungria,Suiza";
        
        euro.anyadirGrupo(lineA);
        int expected = 4;

        assertThat(euro.getNumeroEquipos()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test que comprueba que cuando hay varios grupos, los suma correctamente")
    public void testGetNumberEquiposVariosGrupos(){
        String lineA = "GrupoA:Alemania,Escocia,Hungria,Suiza";
        String lineB = "GrupoB:España,Croacia,Italia,Albania";

        euro.anyadirGrupo(lineA);
        euro.anyadirGrupo(lineB);
        int expected = 8;

        assertThat(euro.getNumeroEquipos()).isEqualTo(expected);
    }
}

    @Nested
    @DisplayName("Tests del método anyadirGrupo")
    class AnyadirGrupoTest{

        @Test
        @DisplayName("Test que comprueba que cuando se recibe un line sin nombre del grupo, lanza una excepción")
        public void testAnyadirEmptyGrupo(){
            String line = "";
            assertThatThrownBy(() -> euro.anyadirGrupo(line))
                    .isInstanceOf(EurocopaException.class);
        }

        @Test
        @DisplayName("Test que comprueba que cuando se intenta añadir un grupo ya existente, lanza una excepción")
        public void testAnyadirGrupoExistente(){
            String lineA = "GrupoA:Alemania,Escocia,Hungria,Suiza";
            String lineB = "GrupoA:Alemania,Escocia,Hungria,Suiza";

            euro.anyadirGrupo(lineA);

            assertThatThrownBy(() -> euro.anyadirGrupo(lineB))
                    .isInstanceOf(EurocopaException.class);
        }

        @Test
        @DisplayName("Test que comprueba que cuando se recibe un parametro invalido, se lanza una excepción")
        public void testAnyadirGrupoInvalido(){
            String line = "GrupoA;Alemania.Escocia.Hungria.Suiza";
            assertThatThrownBy(() -> euro.anyadirGrupo(line))
                    .isInstanceOf(EurocopaException.class);
        }

        @Test
        @DisplayName("Test que comprueba que un grupo se añade correctamente")
        public void testAnyadirGrupoValido(){
            String lineA = "GrupoA:Alemania,Escocia,Hungria,Suiza";
            String lineB = "GrupoB:España,Croacia,Italia,Albania";
            String lineC = "GrupoC:Eslovenia,Dinamarca,Serbia,Inglaterra";

            euro.anyadirGrupo(lineA);
            euro.anyadirGrupo(lineB);
            euro.anyadirGrupo(lineC);

            assertThat(euro.getNumeroEquipos()).isEqualTo(12);
        }
    }

    @Nested
    @DisplayName("Tests del método getEquipo")
    class getEquipoTest{

        @Test
        @DisplayName("Test que comprueba que cuando el grupo pasado por parámetro no existe, se lanza una excepción")
        public void testGetEquipoGrupoInexistente(){
            String grupo = "GrupoA";
            Equipo eq = new Equipo("Alemania");

            assertThatThrownBy(() -> euro.getEquipo(grupo, eq))
                    .isInstanceOf(EurocopaException.class)
                    .hasMessage("El grupo no existe");
        }

        @Test
        @DisplayName("Test que comprueba que cuando no encuentra el equipo, devuelve null")
        public void testGetEquipoInexistente(){
            String line = "GrupoA:Alemania,Escocia,Hungria,Suiza";
            String grupo = "GrupoA";
            Equipo eq = new Equipo("España");

            euro.anyadirGrupo(line);

            assertThat(euro.getEquipo(grupo, eq)).isNull();
        }

        @Test
        @DisplayName("Test que comprueba que cuando encuentra el equipo, lo devuelve correctamente")
        public void testGetEquipoValid(){
            String line = "GrupoA:Alemania,Escocia,Hungria,Suiza";
            String grupo = "GrupoA";
            Equipo eq = new Equipo("Alemania");

            euro.anyadirGrupo(line);

            assertThat(euro.getEquipo(grupo, eq)).isEqualTo(eq);
        }
    }

    @Nested
    @DisplayName("Tests del método anyadirResultado")
    class anyadirResultadoTest{
        
        @Test
        @DisplayName("Test que comprueba que cuando se intenta añadir un resultado de un grupo que no existe, se lanza una excepción")
        public void testAnyadirResultadoGrupoInexistente(){
            when(res.getGrupo()).thenReturn("GrupoC");

            euro.anyadirGrupo("GrupoA:Alemania,Escocia,Hungria,Suiza");

            assertThatThrownBy(() -> euro.anyadirResultado(res))
                    .isInstanceOf(EurocopaException.class)
                    .hasMessage("El grupo no existe");
        }

        @Test
        @DisplayName("Test que comprueba que se añade un resultado a favor del local correctamente")
        public void testAnyadirResultadoGanaLocal(){
            euro.anyadirGrupo("GrupoA:Alemania,Escocia,Hungria,Suiza");
            Equipo eq1 = new Equipo("Alemania");
            Equipo eq2 = new Equipo("Escocia");
            Equipo local = euro.getEquipo("GrupoA", eq1);
            Equipo visitante = euro.getEquipo("GrupoA", eq2);

            when(res.getGrupo()).thenReturn("GrupoA");
            when(res.getLocal()).thenReturn(local);
            when(res.getVisitante()).thenReturn(visitante);
            when(res.ganaLocal()).thenReturn(true);
            when(res.empate()).thenReturn(false);

            
            euro.anyadirResultado(res);

            assertThat(local.getPuntos()).isEqualTo(3);
            assertThat(visitante.getPuntos()).isEqualTo(0);
        }

        @Test
        @DisplayName("Test que comprueba que se añade un resultado de empate correctamente")
        public void testAnyadirResultadoEmpate(){
            euro.anyadirGrupo("GrupoA:Alemania,Escocia,Hungria,Suiza");
            Equipo eq1 = new Equipo("Alemania");
            Equipo eq2 = new Equipo("Escocia");
            Equipo local = euro.getEquipo("GrupoA", eq1);
            Equipo visitante = euro.getEquipo("GrupoA", eq2);
            
            when(res.getGrupo()).thenReturn("GrupoA");
            when(res.getLocal()).thenReturn(local);
            when(res.getVisitante()).thenReturn(visitante);
            when(res.ganaLocal()).thenReturn(false);
            when(res.empate()).thenReturn(true);

            
            euro.anyadirResultado(res);

            assertThat(local.getPuntos()).isEqualTo(1);
            assertThat(visitante.getPuntos()).isEqualTo(1);
        }

        @Test
        @DisplayName("Test que comprueba que se añade un resultado a favor del visitante correctamente")
        public void testAnyadirResultadoGanaVisitante(){
            euro.anyadirGrupo("GrupoA:Alemania,Escocia,Hungria,Suiza");
            Equipo eq1 = new Equipo("Alemania");
            Equipo eq2 = new Equipo("Escocia");
            Equipo local = euro.getEquipo("GrupoA", eq1);
            Equipo visitante = euro.getEquipo("GrupoA", eq2);
            
            when(res.getGrupo()).thenReturn("GrupoA");
            when(res.getLocal()).thenReturn(local);
            when(res.getVisitante()).thenReturn(visitante);
            when(res.ganaLocal()).thenReturn(false);
            when(res.empate()).thenReturn(false);

            
            euro.anyadirResultado(res);

            assertThat(local.getPuntos()).isEqualTo(0);
            assertThat(visitante.getPuntos()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("Tests del método toString")
    class toStringTest{

        @Test
        @DisplayName("Test que prueba que el método toString devuelve el string correctamente")
        public void testToString(){
            String lineB = "GrupoB:España,Croacia,Italia,Albania";
            Equipo eq1 = new Equipo("España");
            Equipo eq2 = new Equipo("Croacia");

            euro.anyadirGrupo(lineB);
            Equipo local = euro.getEquipo("GrupoB", eq1);
            Equipo visitante = euro.getEquipo("GrupoB", eq2);
            String expected = "GrupoB:Albania(0)Croacia(3)España(0)Italia(0)";
            when(res.getGrupo()).thenReturn("GrupoB");
            when(res.getLocal()).thenReturn(local);
            when(res.getVisitante()).thenReturn(visitante);
            when(res.ganaLocal()).thenReturn(false);
            when(res.empate()).thenReturn(false);

            
            euro.anyadirResultado(res);

            assertThat(euro.toString()).hasToString(expected);
        
        }
        
        
    }
    
}