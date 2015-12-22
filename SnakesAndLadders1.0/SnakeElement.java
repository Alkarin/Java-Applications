
/**
 * Class used for the Snake board Element
 * 
 * @author Alexander Vaughan (5448832)
 * @version 12/17/2015
 */
import java.awt.*;
public class SnakeElement extends BoardElement
{
    /**
     * Method to add a background color to SnakeElement
     */
    public SnakeElement(){
        this.setBackground(Color.PINK);
    }
    /**
     * Method to move backwards 5 spaces
     */
    public int moveTo(){
        return -5;
    }
    
    /**
     * Method to print out Board Element
     */
    public String print(){
        return "\\";
    }
}
