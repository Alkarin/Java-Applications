
/**
 * Write a description of class Registration here.
 * 
 * @author Alexander Vaughan (5448832)
 * @version 10/29/2015
 */
import java.util.Scanner;//importing relevant classes to be used in code/program
import java.util.Arrays;
import java.util.ArrayList;
public class Registration//creating Registration class
{
   private String id;//declaring private class variables
   private String password;
   public Registration(String name,String pass)//initializer of Registration class
   {
       id = name;
       password = pass;
   }
   public boolean sameCheck(String id,String password)//method to compare id, password args if they're the same
   {
       if(password.equals(id))//if statement checking if id and password are the same
       {
           return true;//if they are, it returns the boolean true
       }
       return false; //if they are different it returns false
   }
   public boolean lengthCheck(String id,String password)//method to check if args id,password are not less than 6 characters
   {
       if(id.length()<6 && password.length()<6)//if statement checking if id AND password are greater than 6 characters
       {
           return true;//if so return true
       }
       return false;//if not return false
   }
   public boolean userCharacters(String id)//method to see if arg id contains characters !#$
   {
       for(int i = 0; i< id.length(); i++)//for loop that goes through all characters in the String id
       {
           if(id.charAt(i) == '!' || id.charAt(i) == '#' || id.charAt(i) == '$')//if statement checking if any !#$ are contained in the string
           {
               return true;//if so return true
           }
       }
       return false;//if not returns false (because if the loop has gone through and has not returned true, once the loop is over we would just return false
   }
   public boolean passCharacters(String password)//method to check if arg password contains characters !#$
   {
       for(int i = 0; i < password.length(); i++)//for loop that goes through all characters in String password
       {
           if(password.charAt(i) == '!' || password.charAt(i) == '#' || password.charAt(i) == '$')//f statement checking if any characters are !,#,$
           {
               return true;//if so return true
           }
       }
       return false;//if not return false
   }
   public boolean lowCase(String pass)//method to check if arg String pass contains a lowercase character
   {
       String lowerCase = pass.toLowerCase();//creating a 'copy' of the string and making it all lowercase, to be used to compare against initial String pass
       for(int i = 0; i < pass.length(); i++)//for loop to go through all characters in pass String
       {
           if(pass.charAt(i)==lowerCase.charAt(i))//if statement that compares the pass String character to the equivalent lowerCase String character
           {
               return true;//if there is at least one comparison of lower case characters, to return true
           }
       }
       return false;//if not return false
   }
   public boolean upCase(String pass)//method to check if arg String pass contains a upperCase character
   {
       String upperCase = pass.toUpperCase();//creating a 'copy' of the string and making it all uppercase, to be used to compare against initial String pass
       for(int i = 0; i < pass.length(); i++)//for loop to go through all characters in pass String
       {
           if(pass.charAt(i)==upperCase.charAt(i))//if statement that compares the pass String character to the equivalent upperCase String character
           {
               return true;//if there is at least one comparison of upper case characters, to return true
           }
       }
       return false;//if not return false
   }
   public boolean testUser(String pass)//method to cumulate all other boolean methods required to meet required criteria for a registration
   {
       /*
        * This is a very long if statement. We could have just ran these methods in the main method on the upcoming person, but decided against it...for science.
        */
       if(sameCheck(id,password)==false && //if statement with all criteria to meet registration.
            lengthCheck(id,password)==false && 
            userCharacters(id)==false && 
            passCharacters(password)==true && //used && because ALL criteria need to be met for it to be true
            lowCase(pass)==true && 
            upCase(pass)==true)
       {
           return true;//if so return true
       }
       return false;//if not return false
   }
   public static void main(String[] args)//main method! Entry point of program for user
   {
       Scanner keyboard = new Scanner(System.in);//creating a keyboard object of the Scanner class to allow user inputs
       System.out.println("Please Register!");
       System.out.println("To register, your inputs must meet the following criteria:");
       System.out.println("Make sure your User ID and password are not the same");
       System.out.println("Make sure your User ID and password are at least 6 characters long");
       System.out.println("Make sure your User ID does not contain any of the following: !,#,$");
       System.out.println("Make sure your password DOES contain at least one of the following: !,#,$");
       System.out.println("Make sure your password has atleast 1 uppercase and 1 lowercase letter");
       boolean end = false;//declaring variable for do while loop so we can end the loop
       //using a do while loop because we need it to run AT LEAST once
       do//do while loop for user registration
       {
           System.out.println("Please enter your User ID");//requesting user to input an ID 
           String name = keyboard.nextLine();//assigning user input to the String name
           System.out.println("Please enter your password");//requesting user to input a password
           String pass = keyboard.nextLine();//assigning user input to the String pass
           
           //creating a object of the Registration class (naming it person) and having it contain the previously initialized values of name and pass
           Registration person = new Registration(name,pass);
           
           person.testUser(pass);//throwing person object up to testUser method to verify if the inputs meet all the correct criteria
           if(person.testUser(pass)==true)//if testUser method returns true to let user know they inputed their information correctly and are now registered
           {
               System.out.println("You have entered your username and password with all correct conditions");
               System.out.println("You are now Registered.");
               end=true;//if user registration is done, changes end value to true
           }
           else//any other value returned(false) to tell user they have not correctly registered, and to remind them of the conditions that need to be met for a valid registration
           {
               System.out.println("Your inputs do not meet all the correct criteria");
               end=false;//if user registration is not true, stays as false
           }
       }
       while(end==false);//loop continues until end==true
   }
}
