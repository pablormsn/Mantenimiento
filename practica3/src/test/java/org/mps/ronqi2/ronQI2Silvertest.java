/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */
package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mps.dispositivo.DispositivoSilver;

public class ronQI2SilverTest {

    private RonQI2Silver ronQI2Silver;
     @Mock
     private DispositivoSilver disp;
 
     @BeforeEach
     void setUp() {
         disp = Mockito.mock(DispositivoSilver.class);
         ronQI2Silver = new RonQI2Silver();
         ronQI2Silver.anyadirDispositivo(disp);
     }

     
    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    
    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

     @Test
     public void testInicializar() {
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(true);
         assertTrue(ronQI2Silver.inicializar());
         
         when(disp.conectarSensorPresion()).thenReturn(false);
         when(disp.configurarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(true);
         assertFalse(ronQI2Silver.inicializar());

         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(false);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(true);
         assertFalse(ronQI2Silver.inicializar());

         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(false);
         assertFalse(ronQI2Silver.inicializar());

         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(false);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(false);
         assertFalse(ronQI2Silver.inicializar());


         verify(disp, times(5)).conectarSensorPresion();
         verify(disp, times(4)).configurarSensorPresion();
         verify(disp, times(4)).conectarSensorSonido();
         verify(disp, times(4)).configurarSensorSonido();
     }

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */

     @Test
     void reconectar() {
         // Caso 1: El dispositivo está desconectado
         when(disp.estaConectado()).thenReturn(false);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         assertTrue(ronQI2Silver.reconectar());

         when(disp.estaConectado()).thenReturn(false);
         when(disp.conectarSensorPresion()).thenReturn(false);
         when(disp.conectarSensorSonido()).thenReturn(false);
         assertFalse(ronQI2Silver.reconectar());

         when(disp.estaConectado()).thenReturn(false);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(false);
         assertFalse(ronQI2Silver.reconectar());

         when(disp.estaConectado()).thenReturn(false);
         when(disp.conectarSensorPresion()).thenReturn(false);
         when(disp.conectarSensorSonido()).thenReturn(true);
         assertFalse(ronQI2Silver.reconectar());

         // Caso 2: El dispositivo ya está conectado
         when(disp.estaConectado()).thenReturn(true);
         assertFalse(ronQI2Silver.reconectar());
     }

    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */

     
 
     @Test
     void obtenerNuevaLectura() {
         when(disp.leerSensorPresion()).thenReturn(25.0f);
         when(disp.leerSensorSonido()).thenReturn(35.0f);
 
         ronQI2Silver.obtenerNuevaLectura();
         ronQI2Silver.obtenerNuevaLectura();
         ronQI2Silver.obtenerNuevaLectura();
         ronQI2Silver.obtenerNuevaLectura();
         ronQI2Silver.obtenerNuevaLectura();
         ronQI2Silver.obtenerNuevaLectura();
 
         verify(disp, times(6)).leerSensorPresion();
         verify(disp, times(6)).leerSensorSonido();
     }
 
     @Test
     void evaluarApneaSuenyo() {
         when(disp.leerSensorPresion()).thenReturn(25.0f);
         when(disp.leerSensorSonido()).thenReturn(35.0f);
 
         for (int i = 0; i < 5; i++) {
             ronQI2Silver.obtenerNuevaLectura();
         }
 
         assertFalse(ronQI2Silver.evaluarApneaSuenyo());

         when(disp.leerSensorPresion()).thenReturn(10.0f);
         when(disp.leerSensorSonido()).thenReturn(35.0f);
 
         for (int i = 0; i < 5; i++) {
             ronQI2Silver.obtenerNuevaLectura();
         }
 
         assertTrue(ronQI2Silver.evaluarApneaSuenyo());

         when(disp.leerSensorPresion()).thenReturn(25.0f);
         when(disp.leerSensorSonido()).thenReturn(15.0f);
 
         for (int i = 0; i < 5; i++) {
             ronQI2Silver.obtenerNuevaLectura();
         }
 
         assertTrue(ronQI2Silver.evaluarApneaSuenyo());
     }

     @Test
     void estaConectado() {
         when(disp.estaConectado()).thenReturn(true);
         assertTrue(ronQI2Silver.estaConectado());

         when(disp.estaConectado()).thenReturn(false);
         assertFalse(ronQI2Silver.estaConectado()); 
     }
}
