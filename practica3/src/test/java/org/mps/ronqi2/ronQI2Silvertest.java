package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mps.dispositivo.Dispositivo;

public class ronQI2Silvertest {

    
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

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */
    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */

     @Mock
     private Dispositivo dispositivoMock;
 
     private RonQI2Silver ronQI2Silver;
 
     @BeforeEach
     public void setUp() {
         MockitoAnnotations.openMocks(this);
         ronQI2Silver = new RonQI2Silver();
         ronQI2Silver.anyadirDispositivo(dispositivoMock);
     }
 
     @Test
     public void testInicializarExitoso() {
         // Simular conexión y configuración exitosas
         when(dispositivoMock.conectarSensorPresion()).thenReturn(true);
         when(dispositivoMock.configurarSensorPresion()).thenReturn(true);
         when(dispositivoMock.conectarSensorSonido()).thenReturn(true);
         when(dispositivoMock.configurarSensorSonido()).thenReturn(true);
         assertTrue(ronQI2Silver.inicializar());
     }

     @Test
    public void testInicializarFalloConfiguracionPresion() {
        // Simular fallo en la configuración del sensor de presión
        when(dispositivoMock.conectarSensorPresion()).thenReturn(true);
        when(dispositivoMock.configurarSensorPresion()).thenReturn(false); // Configuración fallida
        when(dispositivoMock.conectarSensorSonido()).thenReturn(true);
        when(dispositivoMock.configurarSensorSonido()).thenReturn(true);
        assertFalse(ronQI2Silver.inicializar());
    }

   

    
}
