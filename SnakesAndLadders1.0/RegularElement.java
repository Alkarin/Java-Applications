
/**
 * Class used for the Regular board element
 * 
 * @author Alexander Vaughan (5448832)
 * @version 11/17/2015
 */
import java.awt.*;
public class RegularElement extends BoardElement
{   
    /**
     * Method to add background color to RegularElement
     */
    public RegularElement(){
        this.setBackground(Color.YELLOW);
    }
    /**
     * Moves the user 0 spaces. Keeps them in place
     */
    public int moveTo(){
        return 0;
    }
    
    /**
     * Method to Print out the Board Element
     */
    public String print(){
        return " ";
    }
}
