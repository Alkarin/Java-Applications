
/**
 * Creating a phonebook with ArrayList
 * 
 * @author Alexander Vaughan (5448832)
 * @version 10/22/2015
 */
import java.util.Scanner;//importing relevant classes
import java.util.Arrays;
import java.util.ArrayList;
public class PhoneBookEntry//creating PhoneBookEntry class
{
   private String name;//declaring private variables for a PhoneBookEntry
   private String phoneNum;
   public PhoneBookEntry(String na, String num)//method that constructs what a PhoneBookEntry is
   {
       name = na;//assigns name to whatever the passed value for na was
       phoneNum = num;//assigns name to whatever the passed value for num was
   }
   public void setName(String na)//mutator method to set name
   {
       name = na;//assigns name to whatever the passed value for na was
   }
   public String getName()//accessor method to obtain what name is
   {
       return name;//returns name (as a string)
   }
   public void setNum(String num)//mutator method to set phoneNum
   {
       phoneNum = num;//assigns name to whatever the passed value for num was
   }
   public String getNum()//accessor method to obtain what phoneNum is
   {
       return phoneNum;//returns phoneNum (as a string)
   }
   @Override//Using an @Override method to ensure this executes EVERY TIME no matter what
   public String toString()//method which inherents name and phoneNum because of the @Override
   {
       return ("Name: " + name + "\nPhone Number: " + phoneNum + "\n");//essentially creates the listing of the name and phoneNum,for user.
   }
   public static void main(String[] args)//main method, entry point
   {
       Scanner keyboard = new Scanner(System.in);//creating Scanner input
       
       ArrayList<PhoneBookEntry> people = new ArrayList<PhoneBookEntry>();//arraylist to place people
       System.out.println("Enter 5 different peoples names with their numbers into the Phone Book");//informing user on needed task
       for(int i = 1; i < 6; i++)//for loop to have user input the appropriate info for each PhoneBookEntry
       {
           System.out.println("For entry " + i + ", please enter the persons name");
           String name = keyboard.nextLine();//assigns next line to the name string for the instance of PhoneBootEntry
           System.out.println("Please enter their phone number in the format 000-000-0000");
           String phoneNum = keyboard.nextLine();//assigns next line to the phoneNum string for the instance of PhoneBootEntry
           people.add(new PhoneBookEntry(name,phoneNum));//adds this PhoneBookEntry into the people arrayList
       }
       /*test code for print
        * people.add(new PhoneBookEntry("Alexander","123-456-7890"));
        * people.add(new PhoneBookEntry("Dylan","123-456-7890"));
        * people.add(new PhoneBookEntry("Adam","123-456-7890"));
        * people.add(new PhoneBookEntry("Elah","123-456-7890"));
        * people.add(new PhoneBookEntry("Shannon","123-456-7890"));
       **/
       System.out.println("Here are the entries you have inputted");
       System.out.println("");
       for(PhoneBookEntry person: people)//enhanced for loop to print out the PhoneBookEntrys from the people ArrayList
       {
           System.out.println(person);//each person is a PhoneBookEntry, and thanks to the Override method, which overrites all references of name and phoneNum it will then print out the toString() for each person.
       }
       
   }
}
