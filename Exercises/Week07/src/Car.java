
/**
 * A Program that takes the make, model, and year of your car and gives you the acceleration and brake
 * 
 * @author Alexander Vaughan (5448832) 
 * @version 9/24/2015
 */
import java.util.Scanner;//importing scanner 
public class Car//defining class as car
{//opening class Car code
    private int yearModel;//predefining variables of yearModel
    private int speed = 0;//of speed
    private String make;//of make
    public Car(int yearModel, String make, int speed)//creating car with parameters of year,make,speed
    {//opening car method code
      this.yearModel = yearModel;//constructor assigning predefined variable to new yearmodel variable for Car method
      this.make = make;//constructor assigning predefined variable to new make variable for Car method
      this.speed = speed;//constructor assigning predefined variable to new make variable for Car method
    }//closing car method code
    public int getYM()//method to retrieve yearmodel(accessor)
    {//opening getYM code
        return yearModel;//return of yearModel, set yearModel as int in car,so it can with with this method
    }//closing getYM code
    public String getMake()//method to retrieve Make (accessor)
    {//opening getMake code
        return make;//return of make from Car Method, make sure its a string
    }//closing getMake code
    public int getSpeed()//method to retrieve speed(accessor)
    {//opening getSpeed code
        return speed;//return of speed from Car Method, make sure its an int
    }//closing get Speed code
    public void accelerator()//creating the accelerator mutator
    {//opening accelerator code
        speed = speed + 5;//mutating the speed with + 5 to create the new speed
    }//closing accelerator code
    public void brake()//creating the brake mutator
    {//opening brake code
        speed = speed - 5;//mutating the speed with - 5 to create the new speed
    }//closing brake code
    public static void main(String[] args)//creating the main method, the program the user "sees")
    {//opening main method code
        Scanner keyboard = new Scanner(System.in);//create a new scanner object
        System.out.println("Please Enter the Year Model of a car.");//asking user to input year model
        int year = keyboard.nextInt();//assigning next user input int to year
        keyboard.nextLine();//removing all the 'extra' stuff on the line to not mess up further inputs
        System.out.println("Please enter the make of the car.");//asking user to input make of car
        String make = keyboard.nextLine();//assigning the user input to a string called make
        System.out.println("Please enter the speed of the car.");//asking user to input speed of the car
        int speed = keyboard.nextInt();//assigning the next int input to speed
        keyboard.nextLine();//clearing rest of input line from user
        
        Car car = new Car(year,make,speed);//creating a new car object with parameters of year,make,speed
        System.out.println("The year of your car is" + car.getYM());//telling user what they input for year model using the getYM method accessor
        System.out.println("The make of your car is " + car.getMake());//telling user what they input for car make using the getMake method accessor
        System.out.println("The speed of your car is " + car.getSpeed());//telling user what they input for the speed using the getSpeed method accessor
        
        for(int a = 1; a<4; a++)//creating a for loop to call the accelerator method 3 times
        {//opening for loop code
            car.accelerator();//using the accelerator mutator on our new car object named car
            System.out.println("Your car speed accelerating is " + car.getSpeed());//telling user the new speed of their car object with the accelerator mutation applied
        }//closing for loop code
        for(int b = 1; b<4; b++)//creating a for loop to call the brake method 3 times
        {//opening for loop code
            car.brake();//using the brake mutator on our car object named car
            System.out.println("Your car speed braking is " + car.getSpeed());//telling user the new speed of their car object with the brake mutator applied
        }//closing for loop code
        
        
        
    }//closing main method code
}//closing class code
