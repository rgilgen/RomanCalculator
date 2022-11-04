package ch.css.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator testee = new Calculator();

    @ParameterizedTest
    @CsvSource({"I,I,II", "II,I,III"})
    void  addIIandIreturnsIII(String number1 , String number2, String expectedResult) {
        String result = testee.calculate(number1, number2);

        assertEquals(expectedResult, result);
    }

}