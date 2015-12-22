
/**
 * Creating a lotto game where you guess the winning lotto numbers
 * 
 * @author Alexander Vaughan (5448832)
 * @version 10/13/2015
 */
import java.util.Scanner;//importing classes for code, scanner for keyboard input
import java.util.Random;//for number generation
import java.util.Arrays;//to use arrays
import java.util.ArrayList;//to use array lists
import java.util.Collections;//to use collections class

public class Lottery//class name
{//opening of class code
    private int generator[] = new int[5];//declaring variables for future methods, generator variable array dec
    private int user[] = new int[5];//user input array dec
    private String correct;//string variable dec for correct answers
    private int count = 0;//counter of correct answers variable dec
    
    Lottery()//mutator method to initialise the variable. Helps define the class
    {//opening method code
        genNum();//initializing the getNum method
    }//closing method code
    public void genNum()//lottery machines winning number generator method
    {//opening method code
       ArrayList<Integer> list = new ArrayList<Integer>();//creating an array list named list
       for(int i = 1; i < 11; i++)//for loop that creates the numbers 1-5 for the array list. These values can be made larger to increase the different numbers created.
       {//opening for loop code
           list.add(i);//adds the created number to the ArrayList<Integer> list
       }//closing for loop code
       Collections.shuffle(list);//calls the collections class shuffle method to jumble up the generated numbers, do this to the ArrayList list
       for(int i = 0; i < generator.length;i++)//for loop to assign first 5 shuffled values to the generator array
       {//opening for loop code
           generator[i] = list.get(i);//assigns list values to generator array
       }//closing for loop code
    }//closing method code
    public void userInput(int[] a)//method to assign user input to user array
    {//opening method code
        for(int i = 0 ; i < user.length; i++)//for loop taking the users int inputs and assigning to user array
        {//opening for loop code
            user[i] = a[i];//assigns user array with user inputs
        }//closing for loop code
    }//closing method code
    public int[] getUser()//accessor method for user array
    {//opening method code
        return user;//returns user array
    }//closing method code
    public int[] getGenerator()//accessor method for generator
    {//opening method code
        return generator;//returns generator array
    }//closing method code
    public void compare()//method to compare user input to generated numbers
    {//opening method code
        ArrayList<Integer> array = new ArrayList<Integer>();//creating an array list named array
        for (int i = 0; i < user.length ; i++)//for loop to go through generator values
        {//opening for loop code
            for (int j = 0; j <generator.length; j++)//for loop to go through user values
            {//opening for loop code
                if(generator[i]==user[j])//if user values are same as generator values
                {//opening if code
                    count++;//add one count to correct counts
                    array.add(user[j]);//adds correct user input to ArrayList array
                }//closing if code
            }//closing for loop code
        }//closing for loop code
        correct = array.toString();//taking the array int values and changing them to a string 
    }//closing method code
    public String getCorrect()//accessor to get correct string
    {//opening method code
        return correct;//returns correct string
    }//closing method code
    public int getCount()//accessor to get count
    {//opening method code
        return count;//returns count int variable
    }//closing method code
    public static void main(String[] args)//main method
    {//opening method code
       Scanner keyboard = new Scanner(System.in);//creating new object of scanner class to allow user to input
       ArrayList<Integer> input = new ArrayList<Integer>();//creates ArrayList named input
       Lottery lotto = new Lottery();//creates new Lottery object named lotto which ends up generating the winning numbers
                
       System.out.println("Welcome to the lotto! You will guess 5 numbers and see if you win!");//fluff
       System.out.println("Please enter 5 unique numbers between 1-10");//asks user to put their input ints
        
        for(int i = 1; i < 6 ; i++)//for loop to ask user to input their numbers
        {//opening for loop code
            System.out.println("Number " + i + ": ");//telling user which input of 5 this will be
            int in = keyboard.nextInt();//assigning user input to in int
            if(input.contains(in) == false && in > 0 && in <= 10)//if statement making sure input is not the same and within 1-10
            {//opening if statement code
                input.add(in);//adds the acceptable input (in) to the array list input
            }//closing if statement code
            else//else statement
            {//opening else code
                System.out.println("Please enter a unique number between 1 and 10.");
                i--;//removes 1 count so the loop does not end, making you reput in another number
            }//closing else code
        }//closing for loop code
        
        int[] array = new int[5];//creating an array to put the array list into
        for (int i = 0; i < array.length; i++)//for loop to go through ArrayList array values
        {//opening for loop code
            array[i] = input.get(i);//assigns each ArrayList values to an array named array
        }//closing for loop code
            
        lotto.userInput(array);//throws array to userInput
        
        System.out.println(Arrays.toString(lotto.getUser()));//prints out users picks
        
        lotto.compare();//runs comparison method
        
        System.out.println("You guessed correctly on " + lotto.getCount() + " of your guesses!");//tells user how many times they guessed correctly
        System.out.println(lotto.getCorrect() + " are the numbers you guessed correctly");//tells user WHICH ones they guessed correctly on
        System.out.println("Here are the winning lotto numbers of the day!");//describing next line to user
        System.out.println(Arrays.toString(lotto.getGenerator()));//tells user the actual winning numbers generated by the program    
    }//closing method code
}//closing class code
