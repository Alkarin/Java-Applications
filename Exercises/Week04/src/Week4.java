
/**
 * Calculator for monthly internet service bill pricing
 * 
 * Alexander Vaughan (5448832) 
 * 9/8/2015
 */
import java.util.Scanner;//importing Scanner utility to read keyboard
public class Week4//class
{//coding within class
    public static void main(String[] args)//method
    {//coding within method
        Scanner Keyboard = new Scanner(System.in);//setting scanner object to system.in
        int hours = 0;//predefining variable hour
        double charges = 0;//predefining variable for charges
        System.out.println("Please enter your package letter of A, B, or C.");//asking user to pick a package
        char letter = Keyboard.next().charAt(0);//assigning letter to the first character user inputs
        
        if(letter == 'a' || letter == 'A')//first if statement for a, making sure a and A as valid
        {//statements for if letter ==A
            System.out.println("Enter the hours that you have used.");//asking user to input a int
            hours = Keyboard.nextInt();//hours is assigned to the next int input by user
            if(hours<11)//if the int given by user is less than 11
            {//do the following for the above if
                charges = 9.95;//charges defined by package A
                System.out.println("Your total charges will be " + charges + ".");//printing charges
            }//closing if
            else//if the hours are not <11 it goes to else
            {//do the following for the above else
                charges = (hours-10)*2.00 + 9.95;//charges with the additional hourly charge
                System.out.println("Your total charges will be " + charges + ".");//printing charges
            }//closing else
        
     
    }//closing if statement for A
        if(letter == 'b' || letter == 'B')//second if statement for b,making sure b and B are valid
        {//statements for if letter ==B
            System.out.println("Enter the hours that you have used.");//asking user to input hours
            hours = Keyboard.nextInt();//assigning hours to the next int input by user
            if(hours<21)//if the int given by user is less than 21
            {//do the following for the above if
                charges = 13.95;//charges defined by package B
                System.out.println("Your total charges will be " + charges + ".");//printing charges
            }//closing if
            else//if the hours are not <21 it goes to else
            {//do the following for the above else
                charges = (hours-20)*1.00 + 13.95;//charges with the additional hourly charge
                System.out.println("Your total charges will be " + charges + ".");//printing charges
            }//closing else
        
     
    }//closing if statement for B
        if(letter == 'c' || letter == 'C')//third if statement for c, making sure c and C as valid
        {//statements for if letter ==c
            System.out.println("Enter the hours that you have used.");//asking user to input hours
            hours = Keyboard.nextInt();//assigning hours to the next int input by user
            charges = 19.95;//charges defined by package C
            System.out.println("Your total charges will be " + charges + ".");//printing charges
        }//closing if
        //there is no need for an else statement because package C has no additional charges, the only price possible is 19.95

}//closing method
        }//closing class
