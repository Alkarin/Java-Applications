
/**
 * Program to input your favourite city
 * 
 * Alexander Vaughan (5448832)
 * 9/1/2015
 */
import java.util.Scanner;//importing a tool to allow user input
public class Cities//class 
{//beginning enclosure for class
    public static void main(String[] args)//method
    {//beginning enclosure for method
        Scanner Input = new Scanner(System.in);//named the scanner utility to Input
        System.out.println("Please type in your favourite city!");//asking user to input their city
        String input = Input.nextLine();//allowing the user to input 'words', Line allows it to capture multiple words
        System.out.println(input);//just reprints what the user put in...can be removed to tidy up but good for testing
        System.out.println("So your favourite city is " + input);//telling user their input
        System.out.println("That's not what I would've picked but okay...");//being hilarious
        int length = input.length();//analyzing the user input on how long the input was
        System.out.println("The amount of characters in your city name is " + length +" characters!");//including the space in character amount
        System.out.println("Your city in all uppercase is " + input.toUpperCase());//changing user input to uppercase
        System.out.println("Your city in all lowercase is " + input.toLowerCase());//changing user input to lowercase
        System.out.println("The First letter of your city is " + input.charAt(0));//telling user the first character. charAt is the character AT that part, (0) is the first character
        
    }//closing enclosure for method
}//closing enclosure for class
