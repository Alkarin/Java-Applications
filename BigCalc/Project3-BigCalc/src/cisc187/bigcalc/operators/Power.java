package cisc187.bigcalc.operators;

import cisc187.bigcalc.Operator;
import cisc187.bigcalc.BigInt;

import java.util.Stack;


/**
 * Raises a {@link BigInt} to an integer power.
 *
 * @author Alexander Vaughan (5448832)
 * @version 1.0
 * @since 05-18-2016
 */
public class Power implements Operator {

    /**
     * Checks the operator if it is equal to the addition sign
     * @param operator  the String character checked
     * @return  TRUE if it is the "^" operator otherwise FALSE
     */
    @Override
    public boolean isOperator(String operator) {
        if (operator.equals("^")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Takes the last two operands off the stack and pushes the sum back onto the stack
     * <p>Does not use the token parameter</p>
     * @param operands the subjects of the arithmetic operation
     * @param token Either an operand or operator applied to the operands
     * @throws IllegalArgumentException
     */
    @Override
    public void execute(Stack<String> operands, String token) throws IllegalArgumentException {
        if (operands.size() < 2) {
            throw new IllegalArgumentException();
        }
        int exponent = BigInt.getInt(operands.pop());
        BigInt number = BigInt.valueOf(operands.pop());

        //The sum value to be pushed back onto the stack
        BigInt sum = number.pow(exponent);
        operands.push(sum.toString());
    }
}
