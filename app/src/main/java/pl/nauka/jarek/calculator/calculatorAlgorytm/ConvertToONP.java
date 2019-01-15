/**
 * Converting infix to RPN (shunting-yard algorithm)
 * http://andreinc.net/2010/10/05/converting-infix-to-rpn-shunting-yard-algorithm/
 */

package pl.nauka.jarek.calculator.calculatorAlgorytm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ConvertToONP {
    // Associativity constants for operators
    private static final int LEFT_ASSOC = 0;
    private static final int RIGHT_ASSOC = 1;
    String rownanie[];


    // Supported operators
    private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
    static {
        // Map<"token", []{precendence, associativity}>
        OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("x", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("÷", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("%", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("^", new int[] { 10, RIGHT_ASSOC });
    }

    /**
     * Test if a certain is an operator .
     * @param token The token to be tested .
     * @return True if token is an operator . Otherwise False .
     */
    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    /**
     * Test the associativity of a certain operator token .
     * @param token The token to be tested (needs to operator).
     * @param type LEFT_ASSOC or RIGHT_ASSOC
     * @return True if the tokenType equals the input parameter type .
     */
    private static boolean isAssociative(String token, int type) {
        if (!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
        if (OPERATORS.get(token)[1] == type) {
            return true;
        }
        return false;
    }

    /**
     * Compare precendece of two operators.
     * @param token1 The first operator .
     * @param token2 The second operator .
     * @return A negative number if token1 has a smaller precedence than token2,
     * 0 if the precendences of the two tokens are equal, a positive number
     * otherwise.
     */
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
        // For all the input tokens [S1] read the next token [S2]
        for (String token : inputTokens) {
            if (isOperator(token)) {
                // If token is an operator (x) [S3]
                while (!stack.empty() && isOperator(stack.peek())) {
                    // [S4]
                    if ((isAssociative(token, LEFT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) <= 0)
                            || (isAssociative(token, RIGHT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) < 0)) {
                        out.add(stack.pop()); 	// [S5] [S6]
                        continue;
                    }
                    break;
                }
                // Push the new operator on the stack [S7]
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token); 	// [S8]
            } else if (token.equals(")")) {
                // [S9]
                while (!stack.empty() && !stack.peek().equals("(")) {
                    out.add(stack.pop()); // [S10]
                }
                stack.pop(); // [S11]
            } else {
                out.add(token); // [S12]
            }
        }
        while (!stack.empty()) {
            out.add(stack.pop()); // [S13]
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }

    public static String getEquationInONE(String s)
    {
        //TODO Można udoskonalić lub zmienić na inną metodę

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
