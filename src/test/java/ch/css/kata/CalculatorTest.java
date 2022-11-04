package ch.css.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator testee = new Calculator();
    @Test
    void  addIandIreturnsII() {
        String result = testee.calculate("I", "I");

        assertEquals("II", result);
    }


}