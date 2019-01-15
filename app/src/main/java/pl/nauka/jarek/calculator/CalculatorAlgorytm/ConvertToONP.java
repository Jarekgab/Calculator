/**
 * Converting infix to RPN
 * http://andreinc.net/2010/10/05/converting-infix-to-rpn-shunting-yard-algorithm/
 */

package pl.nauka.jarek.calculator.CalculatorAlgorytm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ConvertToONP {
    private static final int LEFT_ASSOC = 0;
    private static final int RIGHT_ASSOC = 1;


    private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
    static {
        OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("x", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("÷", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("%", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("^", new int[] { 10, RIGHT_ASSOC });
    }

    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    private static boolean isAssociative(String token, int type) {
        if (!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
        if (OPERATORS.get(token)[1] == type) {
            return true;
        }
        return false;
    }

    private static final int cmpPrecedence(String token1, String token2) {
        if (!isOperator(token1) || !isOperator(token2)) {
            throw new IllegalArgumentException("Invalied tokens: " + token1
                    + " " + token2);
        }
        return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
    }

    public static String[] infixToRPN(String[] inputTokens) {
        ArrayList<String> out = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();

        for (String token : inputTokens) {
            if (isOperator(token)) {

                while (!stack.empty() && isOperator(stack.peek())) {

                    if ((isAssociative(token, LEFT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) <= 0)
                            || (isAssociative(token, RIGHT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) < 0)) {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }

                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {

                while (!stack.empty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                stack.pop();
            } else {
                out.add(token);
            }
        }
        while (!stack.empty()) {
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }

    public static String getEquationInONE(String s)
    {
        //TODO Mozna udoskonalic lub zmienic na inna metode

        char[] in = s.toCharArray();
        StringBuilder out = new StringBuilder();

        out.append(in[0]);

        for (int i = 1; i < in.length; i++)
        {
            if ( (in[i] == '(' || in[i] == ')'))
            {
                out.append(" ");
                out.append(in[i]);
            }

            else if ( (in[i] == '÷' || in[i] == 'x' || in[i] == '-' || in[i] == '+') && (in[i-1] != '(' || in[i-1] != ')'))
            {
                out.append(" ");
                out.append(in[i]);
            }

            else if ( ((in[i] >= '0' && in[i] <= '9') || (in[i] == '÷' || in[i] == 'x' || in[i] == '-' || in[i] == '+'))
                    && (in[i-1] == '(' || in[i-1] == ')'))
            {
                out.append(" ");
                out.append(in[i]);
            }

            else if ((in[i] >= '0' && in[i] <= '9') && (in[i-1] == '÷' || in[i-1] == 'x' || in[i-1] == '-' || in[i-1] == '+') )
            {
                if ( (i == 1) && !( (in[0] >= '0') && (in[0] <= '9')) )
                {
                    out.append(in[i]);
                }else
                    {
                        out.append(" ");
                        out.append(in[i]);
                    }
            }
            else
                {
                    out.append(in[i]);
                }
        }
        return String.valueOf(out);
    }
}
