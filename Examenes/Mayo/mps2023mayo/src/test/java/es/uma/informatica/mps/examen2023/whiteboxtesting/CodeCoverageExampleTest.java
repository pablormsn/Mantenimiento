package es.uma.informatica.mps.examen2023.whiteboxtesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CodeCoverageExampleTest {

    CodeCoverageExample ejemplo;

    @BeforeEach
    public void setUp(){
        ejemplo = new CodeCoverageExample();
    }

    @Test
    @DisplayName("Test de genero")
    public void testCodeCoverage(){
        
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.MALE, 60, 90, 190));//1
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.MALE, 60, 90, 160));//2
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.MALE, 60, 70, 190));//3
        assertFalse(ejemplo.methodToTest(CodeCoverageExample.GENDER.MALE, 60, 70, 160));//4
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.MALE, 40, 90, 190));//5
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.MALE, 40, 90, 160));//6
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.MALE, 40, 70, 190));//7
        assertFalse(ejemplo.methodToTest(CodeCoverageExample.GENDER.MALE, 40, 70, 160));//8
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.FEMALE, 60, 90, 190));//9
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.FEMALE, 60, 90, 160));//10
        assertTrue(ejemplo.methodToTest(CodeCoverageExample.GENDER.FEMALE, 60, 70, 190));//11
        assertFalse(ejemplo.methodToTest(CodeCoverageExample.GENDER.FEMALE, 60, 70, 160));//12
        assertFalse(ejemplo.methodToTest(CodeCoverageExample.GENDER.FEMALE, 40, 90, 190));//13
        assertFalse(ejemplo.methodToTest(CodeCoverageExample.GENDER.FEMALE, 40, 90, 160));//14
        assertFalse(ejemplo.methodToTest(CodeCoverageExample.GENDER.FEMALE, 40, 70, 190));//15
        assertFalse(ejemplo.methodToTest(CodeCoverageExample.GENDER.FEMALE, 40, 70, 160));//16
        
    }

    
    

    

}