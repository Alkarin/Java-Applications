
/**
 * Fahrenheit to Celsius Calculator
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Week6printfroundeddecimal
{
    public static double f2c(double A)
    {
        double F = A;
        double C = (((F-32)*5)/9);
        return C;
    }
    public static void main(String[] args)
    {
        Scanner Keyboard = new Scanner(System.in);
        
        for (int n = 0; n<11; n++)
        {
            double result = f2c(n*10);
            System.out.printf((n*10) + " Fahrenheit is %.2f Celsius.\n", result);
            
        }
    }
}
