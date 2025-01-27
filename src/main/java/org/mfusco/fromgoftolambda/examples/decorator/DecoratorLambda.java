package org.mfusco.fromgoftolambda.examples.decorator;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;
/*
    The decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically,
    without affecting the behavior of other objects from the same class.
 */
public class DecoratorLambda {

    public static class DefaultSalaryCalculator implements DoubleUnaryOperator {
        @Override
        public double applyAsDouble( double grossAnnual ) {
            return grossAnnual / 12;
        }
    }

    public static void main( String[] args ) {

        // Decoration Method #1
        new DefaultSalaryCalculator()
                .andThen( Taxes::generalTax )
                .andThen( Taxes::regionalTax )
                .andThen( Taxes::healthInsurance )
                .applyAsDouble( 80000.00 );

        // Decoration Method #2
        calculateSalary( 80000.00, new DefaultSalaryCalculator(), Taxes::generalTax, Taxes::regionalTax, Taxes::healthInsurance );
    }

    public static double calculateSalary(double annualGross, DoubleUnaryOperator... taxes) {
        return Stream.of(taxes)
                .reduce( DoubleUnaryOperator.identity(), DoubleUnaryOperator::andThen )
                .applyAsDouble( annualGross );
    }
}
