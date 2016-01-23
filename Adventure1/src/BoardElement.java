/**
 * Created by alkarin on 1/23/16.
 */
import javax.swing.*;
import java.awt.*;

abstract public class BoardElement extends JLabel {

    public BoardElement(){
        this.setText(print());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(20,20));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);
    }
    public abstract int moveTo();

    public abstract String print();


}
