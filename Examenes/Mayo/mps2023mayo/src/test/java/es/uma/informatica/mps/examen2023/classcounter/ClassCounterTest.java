package es.uma.informatica.mps.examen2023.classcounter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import es.uma.informatica.mps.examen2023.classcounter.dictionary.Dictionary;

import org.mockito.*;

class ClassCounterTest {

    private ClassCounter classCounter;
    
    @Mock
    private Dictionary<String, Long> dictionary;

    @BeforeEach
    public void setUp(){
        dictionary = mock(Dictionary.class);
        Long processingLimit = 10L;
        classCounter = new ClassCounter(dictionary, processingLimit);
    }

    @Test
    @DisplayName("Test del método count con un packageName nulo. Debe lanzar una excepción IllegalArgumentException")
    public void testCountNullPackageName(){
        assertThrows(IllegalArgumentException.class, () -> classCounter.count(null));
    }

    @Test
    @DisplayName("Test del método count con un diccionario vacío. Debe devolver 0")
    public void testCountEmptyDictionary(){
        //Arrange
        when(dictionary.isEmpty()).thenReturn(true);
        String packageName = "org.uma.mps2023";

        //Act
        long resultado = classCounter.count(packageName);

        //Assert
        assertEquals(0, resultado);
        verify(dictionary).isEmpty();
    }

    @Test
    @DisplayName("Test del método count cuando processingLimit es distinto de null y numberOfItems del diccionario es mayor que processingLimit. Debe lanzar IllegalStateException")
    public void testCountNumberOfItemsBiggerThanLimit(){
        when(dictionary.numberOfItems()).thenReturn(11);
        String packageName = "org.uma.mps2023";

        assertThrows(IllegalStateException.class, () -> classCounter.count(packageName), "The package name should not be null");
        
    }

    @Test
    @DisplayName("Test del método count cuando numberOfItems no encuentra la clave en el diccionario. Debe lanzar KeyNotFoundException")
    public void testCountKeyNotFound() throws KeyNotFoundException {
        classCounter = new ClassCounter(dictionary, null);
        String[] keys = {"org.uma.mps2023.factorial", "org.uma.mps2023.person","org.uma.mps2024.factorial", "org.uma.mps2024.person"};
        String packageName = "org.uma.mps2023";

        when(dictionary.numberOfItems()).thenReturn(4);
        when(dictionary.keyStream()).thenReturn(Stream.of(keys));
        when(dictionary.get("org.uma.mps2023.factorial")).thenThrow(KeyNotFoundException.class);
        
        assertThrows(IllegalStateException.class, () -> classCounter.count(packageName));

    }

    @Test
    @DisplayName("Test del método count cuando numberOfItems es menor que el límite")
    public void testCountNumberOfItemsLessThanLimit() throws KeyNotFoundException{
        String[] keys = {"org.uma.mps2023.factorial", "org.uma.mps2023.person", "org.uma.mps2024.factorial", "org.uma.mps2024.person"};
        when(dictionary.numberOfItems()).thenReturn(4);
        when(dictionary.keyStream()).thenReturn(Stream.of(keys));
        when(dictionary.get("org.uma.mps2023.factorial")).thenReturn(2L);
        when(dictionary.get("org.uma.mps2023.person")).thenReturn(4L);
        when(dictionary.get("org.uma.mps2024.person")).thenReturn(5L);
        when(dictionary.get("org.uma.mps2024.factorial")).thenReturn(6L);
        String packageName = "org.uma.mps2023";

        assertEquals(6, classCounter.count(packageName));

        verify(dictionary).isEmpty();
        verify(dictionary).numberOfItems();
        verify(dictionary).keyStream();
        verify(dictionary).get("org.uma.mps2023.factorial");
        verify(dictionary).get("org.uma.mps2023.person");
        verify(dictionary, never()).get("org.uma.mps2024.factorial");
        verify(dictionary, never()).get("org.uma.mps2024.person");
        
        

    }
    

}