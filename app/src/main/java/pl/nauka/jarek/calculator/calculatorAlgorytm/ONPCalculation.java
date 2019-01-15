/**
 * RPN Calculator (using Scala, python and Java)
 * http://andreinc.net/2011/01/03/rpn-calculator-using-python-scala-and-java/
 */

package pl.nauka.jarek.calculator.calculatorAlgorytm;

import java.util.LinkedList;

public class ONPCalculation {

    // List of supported operators
    public static final String[] OPERATORS = { "+", "-", "x", "÷" };

    // Test if a token is operator
    public static Boolean isOperator(String token) {
        for(String op : OPERATORS) {
            if(op.equals(token)) {
                return true;
            }
        }
        return false;
    }

    // Operation based on operator
    public static Double operation(String op, Double e1, Double e2) {
        if(op.equals("+")) {
            return e1 + e2;
        }
        else if(op.equals("-")) {
            return e2 - e1;
        }
        else if(op.equals("x")) {
            return e1 * e2;
        }
        else if(op.equals("÷")) {
            return e2 / e1;

        } else {
            throw new IllegalArgumentException("Invalid operator.");
        }
    }

    // Evaluate RPN expr (given as array of tokens)
    public static Double eval(String[] tokens) {
        LinkedList<Double> stack = new LinkedList<Double>();
        for(String token : tokens) {
            if (isOperator(token)) {
                stack.push(operation(token, stack.pop(), stack.pop()));
            }
            else {
                stack.push(Double.parseDouble(token));
            }
        }
        return stack.pop();
    }
}
