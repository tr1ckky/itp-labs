package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class PriceCalculatorTest {

    @Test
    void noDiscountReturnsOriginalPrice() {
        DiscountPolicy policy = new NoDiscount();
        PriceCalculator calculator = new PriceCalculator(policy);

        double result = calculator.calculate(150.0);

        assertEquals(150.0, result, 1e-9);
    }

    @Test
    void percentDiscountAppliesCorrectly() {
        DiscountPolicy policy = new PercentDiscount(20.0);
        PriceCalculator calculator = new PriceCalculator(policy);

        double result = calculator.calculate(100.0);

        assertEquals(80.0, result, 1e-9);
    }

    @Test
    void calculatorAllowsPolicyReplacement() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(200.0, calculator.calculate(200.0), 1e-9);

        PriceCalculator percentCalculator = new PriceCalculator(new PercentDiscount(10.0));
        assertEquals(90.0, percentCalculator.calculate(100.0), 1e-9);
    }

    @Test
    void throwsExceptionForNegativePrice() {
        DiscountPolicy policy = new NoDiscount();
        PriceCalculator calculator = new PriceCalculator(policy);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(-50.0);
        });

        assertEquals("Цена не может быть отрицательной", exception.getMessage());
    }
}