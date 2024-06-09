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

    private RonQI2Silver ronQI2Silver; //Creamos un objeto de la clase RonQI2Silver

    /*
     * Usamos mock para simular el comportamiento de un objeto real. En este caso, es necesario porque no podemos probar el comportamiento de la clase DispositivoSilver.
     */
    @Mock //Creamos un objeto mock de la clase DispositivoSilver
    private DispositivoSilver disp; //Creamos un objeto de la clase DispositivoSilver

    /*
     * Inicializamos el objeto disp y lo añadimos al objeto ronQI2Silver
     * @BeforeEach se ejecuta antes de cada test
     */
    @BeforeEach
    void setUp() {
        disp = Mockito.mock(DispositivoSilver.class); //Creamos un objeto mock de la clase DispositivoSilver
        ronQI2Silver = new RonQI2Silver(); //Creamos un objeto de la clase RonQI2Silver
        ronQI2Silver.anyadirDispositivo(disp); //Añadimos el objeto disp al objeto ronQI2Silver
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

     /*
      * Se comprueba que el método inicializar funciona correctamente, es decir, que si es posible conectar ambos sensores y configurarlos,
      * hacemos varios bloques cambiando el valor de retorno de los métodos conectarSensorPresion y configurarSensorPresion.
      */
     @Test
     public void testInicializar() {
        // Caso 1: Conexión y configuración correcta de ambos sensores
        when(disp.conectarSensorPresion()).thenReturn(true); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá true
        when(disp.configurarSensorPresion()).thenReturn(true); //Cuando se llame al método configurarSensorPresion de nuestro disp, devolverá true
        when(disp.conectarSensorSonido()).thenReturn(true); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá true
        when(disp.configurarSensorSonido()).thenReturn(true); //Cuando se llame al método configurarSensorSonido de nuestro disp, devolverá true
        assertTrue(ronQI2Silver.inicializar()); //Comprobamos que el método inicializar devuelve true
         
        // Caso 2: Conexión correcta de ambos sensores, pero configuración incorrecta de sensor de presión
        when(disp.conectarSensorPresion()).thenReturn(true); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá true
        when(disp.configurarSensorPresion()).thenReturn(false); //Cuando se llame al método configurarSensorPresion de nuestro disp, devolverá false
        when(disp.conectarSensorSonido()).thenReturn(true); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá true
        when(disp.configurarSensorSonido()).thenReturn(true); //Cuando se llame al método configurarSensorSonido de nuestro disp, devolverá true
        assertFalse(ronQI2Silver.inicializar()); //Comprobamos que el método inicializar devuelve false

        // Caso 3: Conexión correcta de sensor de presión, pero conexión incorrecta de sensor de sonido
        when(disp.conectarSensorPresion()).thenReturn(true); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá true
        when(disp.configurarSensorPresion()).thenReturn(true); //Cuando se llame al método configurarSensorPresion de nuestro disp, devolverá true
        when(disp.conectarSensorSonido()).thenReturn(false); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá true
        when(disp.configurarSensorSonido()).thenReturn(true); //Cuando se llame al método configurarSensorSonido de nuestro disp, devolverá false
        assertFalse(ronQI2Silver.inicializar()); //Comprobamos que el método inicializar devuelve false

        // Caso 4: Conexión correcta de ambos sensores, pero configuración incorrecta de sensor de sonido
        when(disp.conectarSensorPresion()).thenReturn(true); // Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá true
        when(disp.configurarSensorPresion()).thenReturn(true); //Cuando se llame al método configurarSensorPresion de nuestro disp, devolverá true
        when(disp.conectarSensorSonido()).thenReturn(true); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá true
        when(disp.configurarSensorSonido()).thenReturn(false); //Cuando se llame al método configurarSensorSonido de nuestro disp, devolverá false
        assertFalse(ronQI2Silver.inicializar()); //Comprobamos que el método inicializar devuelve false

        //Caso 5: Configuración incorrecta de ambos sensores
        when(disp.conectarSensorPresion()).thenReturn(true); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá true
        when(disp.configurarSensorPresion()).thenReturn(false); //Cuando se llame al método configurarSensorPresion de nuestro disp, devolverá false
        when(disp.conectarSensorSonido()).thenReturn(true); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá true
        when(disp.configurarSensorSonido()).thenReturn(false); //Cuando se llame al método configurarSensorSonido de nuestro disp, devolverá false
        assertFalse(ronQI2Silver.inicializar()); //Comprobamos que el método inicializar devuelve false

        //Caso 6: Conexión incorrecta de ambos sensores, además de configuración incorrecta de sensor de sonido
        when(disp.conectarSensorPresion()).thenReturn(false); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá false
        when(disp.configurarSensorPresion()).thenReturn(true); //Cuando se llame al método configurarSensorPresion de nuestro disp, devolverá true
        when(disp.conectarSensorSonido()).thenReturn(true); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá true
        when(disp.configurarSensorSonido()).thenReturn(true); //Cuando se llame al método configurarSensorSonido de nuestro disp, devolverá true
        assertFalse(ronQI2Silver.inicializar()); //Comprobamos que el método inicializar devuelve false


        //Verify es una función de Mockito que comprueba si se ha llamado al método el número de veces indicado
        verify(disp, times(6)).conectarSensorPresion(); //Comprobamos que el método conectarSensorPresion se ha llamado 6 veces
        verify(disp, times(5)).configurarSensorPresion(); //Comprobamos que el método configurarSensorPresion se ha llamado 5 veces
        verify(disp, times(5)).conectarSensorSonido(); //Comprobamos que el método conectarSensorSonido se ha llamado 5 veces
        verify(disp, times(4)).configurarSensorSonido(); //Comprobamos que el método configurarSensorSonido se ha llamado 4 veces
     }

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */
     @Test
     void reconectar() {
        // Caso 1: El dispositivo está desconectado
        when(disp.estaConectado()).thenReturn(false); //Cuando se llame al método estaConectado de nuestro disp, devolverá false
        when(disp.conectarSensorPresion()).thenReturn(true); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá true
        when(disp.conectarSensorSonido()).thenReturn(true); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá true
        assertTrue(ronQI2Silver.reconectar()); //Comprobamos que el método reconectar devuelve true

        //Caso 2: El dispositivo está desconectado y no se puede conectar ninguno de los dos sensores
        when(disp.estaConectado()).thenReturn(false); //Cuando se llame al método estaConectado de nuestro disp, devolverá false
        when(disp.conectarSensorPresion()).thenReturn(false); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá false
        when(disp.conectarSensorSonido()).thenReturn(false); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá false
        assertFalse(ronQI2Silver.reconectar()); //Comprobamos que el método reconectar devuelve false

        //Caso 3: El dispositivo está desconectado y no se puede conectar el sensor de sonido
        when(disp.estaConectado()).thenReturn(false); //Cuando se llame al método estaConectado de nuestro disp, devolverá false
        when(disp.conectarSensorPresion()).thenReturn(true); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá true
        when(disp.conectarSensorSonido()).thenReturn(false); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá false
        assertFalse(ronQI2Silver.reconectar()); //Comprobamos que el método reconectar devuelve false

        //Caso 4: El dispositivo está desconectado y no se puede conectar el sensor de presión
        when(disp.estaConectado()).thenReturn(false); //Cuando se llame al método estaConectado de nuestro disp, devolverá false
        when(disp.conectarSensorPresion()).thenReturn(false); //Cuando se llame al método conectarSensorPresion de nuestro disp, devolverá false
        when(disp.conectarSensorSonido()).thenReturn(true); //Cuando se llame al método conectarSensorSonido de nuestro disp, devolverá true
        assertFalse(ronQI2Silver.reconectar()); //Comprobamos que el método reconectar devuelve false

        // Caso 5: El dispositivo ya está conectado
        when(disp.estaConectado()).thenReturn(true);
        assertFalse(ronQI2Silver.reconectar());

        //Aquí habría que hacer verify? Diferencias entre mock y stub?
        //Verify es una función de Mockito que comprueba si se ha llamado al método el número de veces indicado
        verify(disp, times(5)).estaConectado(); //Comprobamos que el método estaConectado se ha llamado 5 veces
        verify(disp, times(4)).conectarSensorPresion(); //Comprobamos que el método conectarSensorPresion se ha llamado 4 veces
        verify(disp, times(2)).conectarSensorSonido(); //Comprobamos que el método conectarSensorSonido se ha llamado 2 veces
        
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
        when(disp.leerSensorPresion()).thenReturn(25.0f); //Cuando se llame al método leerSensorPresion de nuestro disp, devolverá 25.0f
        when(disp.leerSensorSonido()).thenReturn(35.0f); //Cuando se llame al método leerSensorSonido de nuestro disp, devolverá 35.0f

        ronQI2Silver.obtenerNuevaLectura(); //Llamamos al método obtenerNuevaLectura
        ronQI2Silver.obtenerNuevaLectura(); 
        ronQI2Silver.obtenerNuevaLectura();
        ronQI2Silver.obtenerNuevaLectura();
        ronQI2Silver.obtenerNuevaLectura();
        ronQI2Silver.obtenerNuevaLectura();
        //lecturasP y lecturasS tienen un tamaño de 5. Al añadir la sexta lectura, se elimina la primera


         verify(disp, times(6)).leerSensorPresion(); //Comprobamos que el método leerSensorPresion se ha llamado 6 veces
         verify(disp, times(6)).leerSensorSonido(); //Comprobamos que el método leerSensorSonido se ha llamado 6 veces
     }
 
     @Test
     void evaluarApneaSuenyo() {
        //Probamos con valores que superan los umbrales
        when(disp.leerSensorPresion()).thenReturn(25.0f); //Cuando se llame al método leerSensorPresion de nuestro disp, devolverá 25.0f
        when(disp.leerSensorSonido()).thenReturn(35.0f); //Cuando se llame al método leerSensorSonido de nuestro disp, devolverá 35.0f

        for (int i = 0; i < 5; i++) { //Realizamos 5 lecturas, que es el número máximo de lecturas que se almacenan
            ronQI2Silver.obtenerNuevaLectura(); //Llamamos al método obtenerNuevaLectura
        }

        assertTrue(ronQI2Silver.evaluarApneaSuenyo()); //El método evaluarApneaSueño devuelve true porque la media de las lecturas de presión y sonido supera los umbrales

        //Probamos ahora con valores que no superan los umbrales
        when(disp.leerSensorPresion()).thenReturn(10.0f); //Cuando se llame al método leerSensorPresion de nuestro disp, devolverá 10.0f
        when(disp.leerSensorSonido()).thenReturn(35.0f); //Cuando se llame al método leerSensorSonido de nuestro disp, devolverá 35.0f

        for (int i = 0; i < 5; i++) { //Realizamos 5 lecturas, que es el número máximo de lecturas que se almacenan
            ronQI2Silver.obtenerNuevaLectura(); //Llamamos al método obtenerNuevaLectura
        }

        assertFalse(ronQI2Silver.evaluarApneaSuenyo()); //El método evaluarApneaSueño devuelve false porque la media de las lecturas de presión no supera el umbral

        when(disp.leerSensorPresion()).thenReturn(25.0f); //Cuando se llame al método leerSensorPresion de nuestro disp, devolverá 25.0f
        when(disp.leerSensorSonido()).thenReturn(15.0f); //Cuando se llame al método leerSensorSonido de nuestro disp, devolverá 15.0f

        for (int i = 0; i < 5; i++) {
            ronQI2Silver.obtenerNuevaLectura(); //Llamamos al método obtenerNuevaLectura
        }

        assertFalse(ronQI2Silver.evaluarApneaSuenyo()); //El método evaluarApneaSueño devuelve false porque la media de las lecturas de sonido no supera el umbral
     }

     //Comprobamos que el método estaConectado funciona correctamente
     @Test
     void estaConectado() {
         when(disp.estaConectado()).thenReturn(true); //Cuando se llame al método estaConectado de nuestro disp, devolverá true
         assertTrue(ronQI2Silver.estaConectado()); //Comprobamos que el método estaConectado devuelve true

         when(disp.estaConectado()).thenReturn(false); //Cuando se llame al método estaConectado de nuestro disp, devolverá false
         assertFalse(ronQI2Silver.estaConectado()); //Comprobamos que el método estaConectado devuelve false
     }
}
