
/**
 * Write a description of class BoardElement here.
 * 
 * @author Alexander Vaughan (5448832)
 * @version 11/17/2015
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
abstract public class BoardElement extends JLabel
{
    //****
    // Method to call all BoardElements (and subclasses of it) to setText
    // to their specifically defined print() methods.
    // This works because while print() is an abstract method in BoardElement
    // all of our Subclass elements all have a defined print() element
    // Because of this we can streamline the process of using these various
    // print() methods and setting them to text here
    // instead of creating this constructor method in every single Element class
    // this saves us time with automation with taking advantage of how inheritance
    // and subclasses work.
    // It is okay to have this method defined here with an abstract print() method
    // because BoardElement is an abstract class, not meant for actual use
    // but as a 'skeletal blueprint' for its subclasses. Since we are not executing
    // or creating BoardElements, but only its subclasses.
    //****
    public BoardElement(){
        this.setText(print());
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(new Dimension(35,35));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);
    }
    
    /**
     * Method to return a number of where to move
     */
    public abstract int moveTo();
    
    /**
     * Method that prints out Element Symbol
     */
    public abstract String print();
}
