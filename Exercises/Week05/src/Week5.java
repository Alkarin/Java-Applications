
/**
 * Playing the HiLo game, which you guess a number between 1 and 100
 * 
 * Alexander Vaughan (5448832)
 * 9/8/2015-1
 */
import java.util.Scanner;//importing Scanner
import java.util.Random;//importing random generator
public class Week5//class
{//coding within class
    public static void main(String[] args)//method
    {//coding within method
        Scanner Keyboard = new Scanner(System.in);//assigning a new keyboard object
        int answer;//declare answer variable
        Random randomNumbers = new Random();//create new Random generator
        answer = randomNumbers.nextInt(99)+1;//setting up answer for 1-100
        int guess = 0;//predefining user input of guess
        boolean end = false;//value set to continue loop
        do//setting up do loop
        {//opening coding for do loop
        System.out.println("Please guess a number between 1-100");//asking user for input 
        guess = Keyboard.nextInt();//assigning users next int as guess
        if(guess>100 || guess<1)//first if statement for if user inputs over 100 or under 1
        {//instructions for the above if statement
            System.out.println("You can't input a number above 100 or below 1!");//telling user they can't do that
            end=false;//allowing the loop to start again
        }//ending instructions for first if statement
        else if(guess>answer)//else if for guess being larger than answer but not above 100 
        {//instructions for the above else if statement
            System.out.println("Too high!");//telling user guess is too high
            end=false;//allowing the loop to start again
        }//ending instructions for first if statement
        else if (guess<answer)//else if for guess being less than answer
        {//instructions for above else if statement
            System.out.println("Too Low!");//telling user guess is too low
            end=false;//allowing the loop to start again
        }//ending instructions for first if statement
        else //if the user inputs anything not stated above (only option left is the actual answer 50)
        {//instructions for above else statement
            System.out.println("Congratulations! The correct answer was " + answer + "!");//telling user they guessed correctly
            end=true;//ENDING THE LOOP/PROGRAM. While is set to keep looping while the answer keeps being false
        }//ending instructions for the above else statement
       
    }
    while (end==false);//setting up while
}//enclosing method coding
}//enclosing class coding
