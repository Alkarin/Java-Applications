
/**
 * Class used for the Ladder board element
 * 
 * @author Alexander Vaughan (5448832)
 * @version 11/17/2015
 */
import java.awt.*;
public class LadderElement extends BoardElement
{
    /**
     * Method that adds background color to LadderElement
     */
    public LadderElement(){
        this.setBackground(Color.GREEN);
    }
    /**
     * Method to move forward 5 spaces
     */
    public int moveTo(){
        return 5;
    }
    
    /**
     * Method to print out Board Element
     */
        public String print(){
        return "#";
    }
}
