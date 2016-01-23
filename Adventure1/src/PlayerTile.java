import java.awt.*;

/**
 * Created by alkarin on 1/23/16.
 */
public class PlayerTile extends BoardElement {

    private int playerPosX = 0;
    private int playerPosY = 0;

    public PlayerTile(){
        this.setBackground(Color.LIGHT_GRAY);
    }

    public int moveTo(){
        return 0;
    }

    public String print(){
        return "X";
    }

    public void setPlayerPosX(int a){
        this.playerPosX = a;
    }

    public void setPlayerPosY(int a){
        this.playerPosY = a;
    }

    public int getPlayerPosX(){
        return playerPosX;
    }

    public int getPlayerPosY(){
        return playerPosY;
    }
}
