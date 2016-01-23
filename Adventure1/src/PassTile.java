import java.awt.*;

/**
 * Created by alkarin on 1/23/16.
 */
public class PassTile extends BoardElement {

    public PassTile(){
        this.setBackground(Color.LIGHT_GRAY);
    }

    public int moveTo() {
        return 0;
    }

    public String print() {
        return ".";
    }


}
