
/**
 * A program that tosses a coin 20 times and gives you how many times it landed on either side
 * 
 * @author Alexander Vaughan (5448832) 
 * @version 10/1/2015
 */
import java.util.Random;//importing the Random 
public class Coin//class of coin
{
    private String sideUp;//creating the sideUp variable, which is needed in the getSide later
    Coin()//method to initialize the coin with a side
    {//opening code for the coin method
       toss();//statement of using the later toss method to initialize the coin
    }//closing code for the coin method

    public void toss()//method to flip a coin object
    {//opening code for the toss method
        Random coinflip = new Random();//creating a new object of the random class named coinflip
        int rng = coinflip.nextInt(2);//assigning an integer rng using the random, to get an int between of 0 or 1
        if (rng == 0)//if statement saying if that rng integer comes out as 0 do the following
        {//opening code for if statement
            sideUp = "Heads";//if the integer is 0 assign the sideUp to the string Heads
        }//closing code for if statement
        else //if the integer comes up as anything that isn't 0 (which can only be 1)
        {//opening code for else statement
            sideUp = "Tails";//assigning the sideUp variable to a string called Tails
        }//closing code for else statement
        
    }//closing code for the toss method
    
    public String getSide()//method to retrive the value of the SideUp String
    {//opening code for getSide method
        return sideUp;//getting the value for sideUp
    }//closing code for getSide method
    
    public static void main(String[] args)//main method to actually use the previous methods
    {//opening main method code
        Coin coin = new Coin();//creating a new coin object of the Coin class
        System.out.println("The initial side of the coin is " + coin.getSide());//using the getSide method to tell user coins initial facing
        int heads = 0;//setting counter for how many times the coin flips heads
        int tails = 0;//setting counter for how many times the coin flips tails
        
        for(int i=1;i<21;i++)//creating a for loop to run 20 times, to simulate the coin flipping 20 times
        {//opening for loop code
            coin.toss();//using the toss method on our coin object
            if (coin.getSide()=="Heads")//if statemtn for if the coin toss brings up heads do the following
            {//opening code for if 
                heads++;//adds 1 count to the heads int because it has come up heads
                System.out.println("Coin toss " + i + " came up as " + coin.getSide()); //tells user for this specific coin flip what the coin came up as
            }//closing code for if
            else//else statement if coin does not come up as heads
            {//opening code for else
                tails++;//adds 1 count to the tails int because it has come up tails
                System.out.println("Coin toss " + i + " came up as " + coin.getSide());//tells user for this specific coin flip what the coin came up as
            }//closing code for else
            
    }//closing for loop code
    System.out.println("The total amount of times the coin came up as heads was " + heads +//telling user how many total times each side came up
     " times.\nThe total amount of times the coin came up as tails was " + tails + " times.");//when the coin was flipped
    }//closing main method code
}//closing class code

