package cisc187.bigcalc.operators;

import cisc187.bigcalc.Operator;
import cisc187.bigcalc.BigInt;

import java.util.Stack;

/**
 * Executes the multiplication command on two {@link BigInt}s.
 *
 * @author Alexander Vaughan (5448832)
 * @version 1.0
 * @since 05-18-2016
 */
public class Multiply implements Operator {

    /**
     * Checks the operator if it is equal to the addition sign
     * @param operator  the String character checked
     * @return  TRUE if it is the "*" operator otherwise FALSE
     */
    @Override
    public boolean isOperator(String operator) {
        if (operator.equals("*")) {
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
        BigInt one = BigInt.valueOf(operands.pop());
        BigInt two = BigInt.valueOf(operands.pop());

        ////The sum value to be pushed back onto the stack
        BigInt sum = one.times(two);
        operands.push(sum.toString());
    }

}
