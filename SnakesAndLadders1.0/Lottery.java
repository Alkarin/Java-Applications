
/**
 * Creating a lotto game where you guess the winning lotto numbers
 * 
 * @author Alexander Vaughan (5448832)
 * @version 10/13/2015
 */
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Lottery
{
    private int generator[] = new int[5];
    private int user[] = new int[5];
    private String correct;
    private int count = 0;
    
       Lottery()
    {
        genNum();
    }
    public void genNum()
    {
       ArrayList<Integer> list = new ArrayList<Integer>();
       for(int i = 0; i < 5; i++)
       {
           list.add(i);
       }
       Collections.shuffle(list);
       int[] a = new int[5];
       for(int i = 0; i < a.length; i++)
       {
           a[i] = a[i];
       }
       for(int i = 0; i < generator.length;i++)
       {
           generator[i] = a[i];
       }
    }
    public void userInput(int[] a)
    {
        for(int i = 0 ; i < user.length; i++)
        {
            generator[i] = a[i];
        }
    }
    public int[] getUser()
    {
        return user;
    }
    public int[] getGenerator()
    {
        return generator;
    }
    public void compare()
    {
        ArrayList<Integer> array = new ArrayList<Integer>();
        
        for (int i = 0; i < user.length ; i++)
        {
            for (int j = 0; j<generator.length; j++)
            {
                if(generator[i]==user[j])
                {
                    count++;
                    array.add(user[j]);
                }
            }
        }
        correct = array.toString();
    }
    public String getCorrect()
    {
        return correct;
    }
    public int getCount()
    {
        return count;
    }
            public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        int[] pick = new int[5];
        
        Lottery lotto = new Lottery();
                
        System.out.println("Welcome to the lotto! You will guess 5 numbers and see if you win!");
             
        System.out.println("Please enter your first guess between 0 and 9");
        pick[0]= keyboard.nextInt();
        System.out.println("Please enter your second guess between 0 and 9");
        pick[1]= keyboard.nextInt();
        System.out.println("Please enter your third guess between 0 and 9");
        pick[2]= keyboard.nextInt();
        System.out.println("Please enter your fourth guess between 0 and 9");
        pick[3]= keyboard.nextInt();
        System.out.println("Please enter your fifth and final guess between 0 and 9");
        pick[4]= keyboard.nextInt();
        
        System.out.println(Arrays.toString(lotto.getUser()));
        
        lotto.compare();
        
        System.out.println("You guessed correctly on " + lotto.getCount() + " of your guesses!");
        System.out.println(lotto.getCorrect() + " are the numbers you guessed correctly");
        System.out.println("Here are the winning lotto numbers of the day!");
        System.out.println(Arrays.toString(lotto.getGenerator()));
        
        
        
        
        
    }
}
