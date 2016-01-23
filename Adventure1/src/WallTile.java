import java.awt.*;

/**
 * Created by alkarin on 1/23/16.
 */
public class WallTile extends BoardElement {

    public WallTile(){
        this.setBackground(Color.DARK_GRAY);
    }

    public int moveTo() {
        return 0;
    }

    public String print() {
        return "[]";
    }
}
