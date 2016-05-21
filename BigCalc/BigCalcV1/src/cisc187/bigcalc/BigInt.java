package cisc187.bigcalc;

import java.util.*;
import java.util.List;

/**
 * Simulates Integers of (nearly) unlimited size.
 * <p>
 * A BigInt is a sequence of digits. Each digit is a Integer element within a List
 * The BigInt class stores the sequence and allows basic arithmetic operations on it's value.
 * </p>
 * <p>Can be created by passing numerical arguments in a List or String Data Type</p>
 *
 * @author Alexander Vaughan (5448832)
 * @version 1.0
 * @since 05-18-2016
 */
public final class BigInt {

    /**
     * static BigInt with a value of 1
     * <p>Predominantly for unit testing</p>
     */
    public static final BigInt ONE = new BigInt("1");

    /**
     * static BigInt with a value of 0
     * <p>Predominantly for unit testing</p>
     */
    public static final BigInt ZERO = new BigInt("0");

    /**
     * The digit values stored in this BigInt
     */
    private final List<Integer> digit = new ArrayList<>();

    /**
     * Private no arg constructor to prevent JVM default constructor
     */
    private BigInt() {
    }

    /**
     * BigInt constructor that takes a List of Integers and stores them in its digits List
     * <p>Removes all leading 0's from the list. If the list is all 0's will return add "0"</p>
     * @param list  the list to be
     */
    private BigInt(List<Integer> list) {
        Iterator<Integer> it = list.iterator();

        //could be while loop
        int count = 0;
        if (list.size() < 1) {
            while (it.hasNext() && it.next() == 0) {
                it.remove();
                count++;
            }
        }
        this.digit.addAll(list);
    }

    /**
     * BigInt constructor that takes a String, removes extraneous characters
     * and adds it as elements into the List of digits.
     *
     * <p>Removes all leading zeros from the string.
     * If the String is all O's will return single digit"0"</p>
     * @param str   The String parsed into Integers and added to BigInt
     */
    private BigInt(String str) {
        if (str.contains("-")) {
            throw new NumberFormatException();
        }
        String tmp = str.replaceAll("[^\\d.]", "");
        int count = 0;
        //Checks for leading O's and increments count when found
        if (tmp.length() != 0) {
            if (tmp.length() == 1) {
                //Checks if string is a single value of 0
                if (tmp.equals("0")) {
                    count = 0;
                }
            } else {
                for (int i = 0; i < tmp.length(); i++) {
                    if (i == tmp.length() - 1) {
                        break;
                    } else if (str.charAt(i) == '0') {
                        count++;
                    } else {
                        break;
                    }
                }
            }
        }
        //Splits String into array elements and adds to List
        String[] s = tmp.split("");
        List<String> list = new ArrayList<>(Arrays.asList(s));
        for (int i = count; i < list.size(); i++) {
            int b = Integer.valueOf(list.get(i));
            this.digit.add(b);
        }
    }

    /**
     * Static constructor that creates a new BigInt
     * @param digits    The String to be parsed into Integer elements
     * @return  returns a new BigInt with a list of Integer values from digits
     */
    public static BigInt valueOf(String digits) {
        return new BigInt(digits);
    }

    /**
     * Plus takes another BigInt and adds its values to the current BigInt
     * @param operand   The BigInt to be added
     * @return  returns a new BigInt of the previous BigInts added together
     */
    public BigInt plus(BigInt operand) {
        List<Integer> a = this.getDigit();
        List<Integer> b = operand.getDigit();
        List<Integer> sum = new ArrayList<>();

        //ensure the largest int is first arg passed
        if (a.size() > b.size()) {
            sum = add(a, b, 0, sum);
        } else {
            sum = add(b, a, 0, sum);
        }
        return new BigInt(sum);
    }

    /**
     * Multiplies another BigInt to the current BigInt and returns a new BigInt from the value multiplied
     * @param operand   The BigInt to be multiplied
     * @return  returns a new BigInt of the previous BigInts multiplied together
     */
    public BigInt times(BigInt operand) {
        BigInt sum = new BigInt("0");
        List<Integer> a = this.getDigit();
        List<Integer> b = operand.getDigit();
        Deque<BigInt> temp = new ArrayDeque<>();
        //Holds temporary BigInts from multiplying each digit
        Deque<BigInt> temp1;

        //ensure largest int is first arg passed
        if (a.size() > b.size()) {
            temp1 = multiply(b, a, temp);
            //adds all temporary BigInts
            for (int i = 0; i < temp1.size(); i++) {
                temp1.add(addZeros(temp1.pop(), i));
            }
            for (BigInt addit : temp1) {
                sum = sum.plus(addit);
            }
        } else {
            temp1 = multiply(b, a, temp);
            for (int i = 0; i < temp1.size(); i++) {
                temp1.add(addZeros(temp1.pop(), i));
            }
            for (BigInt addit : temp1) {
                sum = sum.plus(addit);
            }
        }
        return new BigInt(sum.toString());
    }

    /**
     * Multiplies a BigInt by itself as many times as the exponent
     * @param exponent  The amount of times BigInt is to be multiplied by itself
     * @return  returns a new BigInt from the value of it multiplying itself by the specified amount of times
     */
    public BigInt pow(int exponent) {
        //arithmetic with absolute answers
        if (exponent == 1) {
            return new BigInt(this.digit);
        } else if (exponent == 0) {
            return new BigInt("1");
        }
        //BigInt of same value, to multiply against
        BigInt one = new BigInt(this.digit);
        if (exponent == 2) {
            return this.times(one);
        }

        BigInt two = new BigInt(this.digit);
        BigInt exp = one.times(two);

        //Multiplies BigInt
        for (int i = 0; i < exponent - 2; i++) {
            exp = exp.times(two);
        }
        return new BigInt(exp.toString());
    }

    /**
     * Returns the current state of the BigInts digit values
     * @return  List of Integer values of each digit in the BigInt
     */
    public List<Integer> getDigit() {
        if (this.digit.size() == 0) {
            this.digit.add(0);
        }
        return this.digit;
    }

    /**
     * Recursive method to perform addition
     * @param x The digit values of a BigInt
     * @param y The digit values of a secondary BigInt
     * @param carry The value in excess of 10 from a added integer
     * @param result The final digit list of the summed BigInts
     * @return  Returns the sum of adding two BigInts
     */
    private List<Integer> add(List<Integer> x, List<Integer> y, final int carry, List<Integer> result) {
        int sum;
        int c = carry;
        //adds initial digits
        sum = getSum(x, y, c);
        //places initial digit
        result.add(0, sum % 10);
        //places excess of 10
        c = sum / 10;
        //base case
        if (y.size() == 0) {
            if (x.size() == 0) {
                if (c == 0) {
                    return result;
                } else {
                    result.add(0, c);
                    return result;
                }
            //add any other x elements
            } else {
                List<Integer> temp = new ArrayList<>();
                final int initSize = x.size() - 1;
                for (int i = 0; i <= initSize; i++) {
                    temp.add(0);
                    //compute sum
                    sum = getSum(x, y, c);
                    //compute carry
                    c = sum / 10;
                    result.add(0, sum % 10);
                }
                if (c == 0) {
                    return result;
                } else {
                    result.add(0, c);
                    return result;
                }
            }
        }
        //recursive case
        return this.add(x, y, c, result);
    }

    /**
     * Recursive method to perform multiplication
     * @param x The digit values of a BigInt
     * @param y The digit values of a secondary BigInt
     * @param result The values of the product of multiplying the BigInts
     * @return  Returns the product of multiplying two BigInts
     */
    private Deque<BigInt> multiply(List<Integer> x, List<Integer> y, Deque<BigInt> result) {
        List<Integer> tmp = new ArrayList<>();
        tmp.addAll(x);
        //base case
        if (y.isEmpty()) {
            return result;
        }
        //decrement y
        int lsb = y.remove(y.size() - 1);
        for (int i = 0; i < tmp.size(); i++) {
            //do multiplication
            result.add(new BigInt(scalarMultiply(lsb, x)));
            tmp.clear();
        }

        if (y.size() == 0) {
            return result;
        }
        //recursive case
        return this.multiply(x, y, result);
    }

    /**
     * Does the actual addition arithmetic of two list values
     * @param x The values of a BigInt
     * @param y The values of a Secondary BigInt
     * @param carry The temporary value in excess of 10 from the addition
     * @return Returns a single digit int from the preceding addition
     */
    private int getSum(List<Integer> x, List<Integer> y, final int carry) {
        //get digit at last index
        int i1 = x.remove(x.size() - 1);
        int i2 = 0;
        if (y.size() > 0) {
            i2 = y.remove(y.size() - 1);
        }
        int i3 = carry;
        //adds any potentially carry
        return i1 + i2 + i3;
    }

    /**
     * Does the actual multiplication arithmetic of a integer
     * <p>Multiples a single digit against all integers of the accompanying BigInt</p>
     * @param scalar    The integer to multiply
     * @param x The integer values to multiply against
     * @return Returns a new List of Integer values from multiplying x by scalar
     */
    private List<Integer> scalarMultiply(final int scalar, List<Integer> x) {
        final int initSize = x.size();
        int carry = 0;
        //single digit multiplication product
        int prod = 0;
        //final multiplication product
        List<Integer> product = new ArrayList<>();
        for (int i = initSize; i > 0; i--) {
            prod = scalar * x.get(i - 1) + carry;
            carry = prod / 10;
            //adds least significant number
            product.add(0, prod % 10);
        }
        //adds any extraneous carry to the beginning of the number
        if (carry > 0) {
            product.add(0, carry);
        }
        return product;
    }

    /**
     * After multiplcation adds all the following zeros based on exponential numberical increase
     * from the arithmetic
     *
     * @param b The BigInt to add zeros to
     * @param count The amount of zeros to add to BigInt
     * @return  returns the previous BigInt with the appropriate amount of zeros at the end of its value
     */
    public BigInt addZeros(BigInt b, int count) {
        for (int i = 0; i < count; i++) {
            b.getDigit().add(0);
        }
        return b;
    }

    /**
     * Transforms a String character into an integer while removing
     * any potential unwanted characters
     * <p>IE: "6" to 6</p>
     * @param s The String to be enlightened
     * @return  returns an integer value of a string
     */
    public static int getInt(String s) {
        String str = s.replaceAll("[^\\d.]", "");
        //purposefully no try catch
        int arg = 0;
        arg = Integer.parseInt(str);
        return arg;
    }

    /**
     * toString method to print out the values of the BigInt
     * @return  returns a String of all values in the  BigInt
     */
    @Override
    public String toString() {
        int s = digit.size() - 1;
        StringBuilder sb = new StringBuilder();
        if (this.digit.size() == 1 && this.digit.get(0) == 0) {
            return digit.get(0).toString();
        }
        for (int i = 0; i <= s; i++) {
            if (digit.isEmpty()) {
                break;
            }
            int v = digit.get(i);
            sb.append(Integer.toString(v));
        }
        return sb.toString();
    }

    /**
     * Checks if two BigInts are equal to each other based on their List of Integer values
     * @param obj BigInt to be compared to
     * @return TRUE if same values, FALSE otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!BigInt.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final BigInt other = (BigInt) obj;

        if ((this.digit == null) ? (other.getDigit() != null) : !this.digit.equals(other.getDigit())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * HashCode to identify specific BigInt
     * @return returns hashCode value
     */
    @Override
    public int hashCode() {
        int hash = 11081993;
        hash = 14 * hash + Integer.valueOf(this.digit.toString());
        return hash;
    }
}
