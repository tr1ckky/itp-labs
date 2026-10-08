package edu.course.lab01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }
    
    @Test
    void returnsTrueForZero() {
        assertTrue(CourseToolkit.isEven(0));
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        assertTrue(CourseToolkit.isEven(-8));
    }

    @Test
    void isPrimeReturnsFalseForNumberLessTwo() {
        assertFalse(CourseToolkit.isPrime(1));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(-6));
    }
    
     @Test
    void isPrimeReturnsTrueForTwoAndThree() {
        assertTrue(CourseToolkit.isPrime(2));
        assertTrue(CourseToolkit.isPrime(3));
    }

     @Test
    void isPrimeReturnsFalseForCompositeNumbersAndSquares() {
        assertFalse(CourseToolkit.isPrime(4));
        assertFalse(CourseToolkit.isPrime(49));
    }

     @Test
    void isPrimeReturnsTrueForLargePrime() {
        assertTrue(CourseToolkit.isPrime(17));
    }

     @Test
    void isPalindromeReturnsTrueForValidPalindromes() {
        assertTrue(CourseToolkit.isPalindrome("radar"));
        assertTrue(CourseToolkit.isPalindrome("a"));
        assertTrue(CourseToolkit.isPalindrome(""));
    }

    @Test
    void isPalindromeReturnsFalseForNonPalindromesAndCaseSensitivity() {
        assertFalse(CourseToolkit.isPalindrome("hello"));
        assertFalse(CourseToolkit.isPalindrome("Radar"));
    }

    @Test
    void isPalindromeThrowsExceptionForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void averageReturnsCorrectValueForPositiveNumbers() {
        int[] numbers = {1, 2, 3, 4, 5};
        assertEquals(3.0, CourseToolkit.average(numbers), 0.0001);
    }

    @Test
    void averageReturnsCorrectValueForNegativeNumbers() {
        int[] numbers = {-10, -20};
        assertEquals(-15.0, CourseToolkit.average(numbers), 0.0001);
    }

    @Test
    void averageThrowsExceptionForNullOrEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[0]));
    }
}
