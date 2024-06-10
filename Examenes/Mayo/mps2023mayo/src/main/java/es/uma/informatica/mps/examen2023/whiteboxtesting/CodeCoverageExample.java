package es.uma.informatica.mps.examen2023.whiteboxtesting;

public class CodeCoverageExample {
  enum GENDER {MALE, FEMALE}

  public boolean methodToTest(GENDER gender, int age, int weight, int height) {
    boolean result = false ;
    if (((gender == GENDER.MALE) || (age > 50)) && ((weight > 80) || (height > 180))) {
      result = true ;
    }

    return result;
  }
}
/* GENDER | age | weight | height | result
    * --------------------------------
   1*   V       V      V        V       V +
   2*   V       V      V        F       V +
   3*   V       V      F        V       V +
   4*   V       V      F        F       F +
   5*   V       F      V        V       V +
   6*   V       F      V        F       V +
   7*   V       F      F        V       V +
   8*   V       F      F        F       F +
   9*   F       V      V        V       V +
  10*   F       V      V        F       V +
  11*   F       V      F        V       V +
  12*   F       V      F        F       F +
  13*   F       F      V        V       F +
  14*   F       F      V        F       F +
  15*   F       F      F        V       F +
  16*   F       F      F        F       F +
    * 
    */