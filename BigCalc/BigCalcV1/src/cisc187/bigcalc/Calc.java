package cisc187.bigcalc;


import cisc187.bigcalc.operators.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * An arbitrary precision Integer calculator that uses
 * PostFix notation.
 * <p>
 * <p>
 * This class is the main entrance point for the program.
 * It takes input from "Standard input" and prints results to "Standard output".
 * </p>
 *
 * <p>Specifically designed to implement recursion and to display
 * the usefulness of using Javas predefined BigInteger class</p>
 *
 * <p>@TODO add more dynamic handling of character input</p>
 * <p>@TODO Performance Optimization</p>
 * <p>@TODO Add negative number arithmetic</p>
 * <p>@TODO Add division arithmetic</p>
 *
 * @author Alexander Vaughan (5448832)
 * @version 1.0
 * @since 05-18-2016
 *
 * @see <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia entry for Postfix notation</a>
 * @see <a href="http://c2.com/cgi/wiki?PostfixNotation">http://c2.com/cgi/wiki?PostfixNotation</a>
 */
public class Calc {
    /**
     * The numerical and operator values to be calculated upon
     */
    private Stack<String> operands;
    /**
     * List of operators to call for specific arithmetic executions
     */
    private static List<Operator> operators;
    /**
     * Predefined error prompt
     */
    private final String defaultResult = "Error: could not produce a result.";
    /**
     * Predefined exception notification
     */
    private final String illegalArgException = "java.lang.IllegalArgumentException: Attempting to add with fewer than 2 operands.";

    /**
     * constructor to initialize Calc with operators
     */
    public Calc() {
        initOperators();
    }

    /**
     * Creates all appropriate arithmetic classes and adds to Calc for flexibility
     */
    private void initOperators() {
        operators = new ArrayList<Operator>();
        operators.add(new OperandPusher());
        operators.add(new Add());
        operators.add(new Multiply());
        operators.add(new Power());
    }

    /**
     * Entry point of program.
     * <p>Processes standard input and when it finds an appropriate
     * expression it calls Calculate() to perform the arithmetic and
     * print out the mathematical answer</p>
     *
     * <p>Able to read multiple lines for a single expression. If a line of input is blank,
     * assumes expression is finished being inserted and calcuates</p>
     *
     * <p>Is able to continue reading lines for future inputs</p>
     *
     * <p>Will continue to read input until file is empty or user types in "EXIT"</p>
     * @param args  Standard input from user or file
     */
    public static void main(String[] args) {
        Calc calc = new Calc();
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String input = "";
        //List of all operands and operators found for single expression
        List<String> strs = new ArrayList<>();
        ArrayList<String> tokens;
        //this makes sure an entire problem is added as a tokens element
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                //concatenates strs for multiple line input
                for (String s : strs) {
                    sb.append(s);
                    input = sb.toString();
                }
                tokens = new ArrayList<>(Arrays.asList(input.split(" ")));
                //call calculate and reset
                calc.calculate(tokens);
                strs.clear();
                tokens.clear();
                sb.setLength(0);
                input = "";
            } else if (line.length() == 1) {
                strs.add(" ");
                strs.add(line);
                strs.add(" ");
            //manual user exit case
            } else if (line.equals("EXIT")){
                System.exit(0);
            }else {
                strs.add(line);
            }
        }
    }

    /**
     * Performs calculations on operands and operators by calling on the operators list.
     * <p>Return value mostly for unit testing</p>
     * @param tokens    List of values to be calculated
     * @return  returns the value from the calculations
     */
    public String calculate(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            tokens.removeAll(Arrays.asList(""));
        }
        String result = processOperands(tokens);

        if (result.equals(null)) {
            System.out.println(result);
            return defaultResult;
        } else if (result.equals("")) {
            return "";
        } else if (result.equals(illegalArgException)) {
            System.out.println(result);
            return illegalArgException;
        } else {
            System.out.println(result);
            return result.replaceAll("[^\\d.]", "");
        }
    }

    /**
     * Processes the operands and checks for operators. Once the appropriate
     * operator is found, calls the appropriate execute method
     * <p>Return value mostly for unit testing</p>
     * @param tokens    operands to process
     * @return  Returns the string value operands
     */
    private String processOperands(List<String> tokens) {
        operands = new Stack<>();
        for (String token : tokens) {
            for (Operator o : operators) {
                //checks if operands is an operator
                if (o.isOperator(token)) {
                    try {
                        o.execute(operands, token);
                    } catch (IllegalArgumentException e) {
                        return illegalArgException;
                    }
                }
            }
        }
        return operands.toString().replaceAll("[^\\d.]", "");
    }
}
