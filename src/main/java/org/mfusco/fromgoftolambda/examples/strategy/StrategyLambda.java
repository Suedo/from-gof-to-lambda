package org.mfusco.fromgoftolambda.examples.strategy;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/*
    The strategy pattern (also known as the policy pattern) is a behavioral software design pattern that enables
    selecting an algorithm at runtime. Instead of implementing a single algorithm directly, code receives run-time
    instructions as to which in a family of algorithms to use.
 */
public class StrategyLambda {

    public static void publishText( String text,
                                    Predicate<String> filter,
                                    UnaryOperator<String> format) {
        if (filter.test( text )) {
            System.out.println( format.apply( text ) );
        }
    }

    public static void main( String[] args ) {
        publishText( "ERROR - something bad happened", s -> true, String::toUpperCase );
        publishText( "DEBUG - I'm here", s -> s.length() < 20, String::toLowerCase );
    }
}
