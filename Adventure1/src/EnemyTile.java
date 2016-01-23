import java.awt.*;

/**
 * Created by alkarin on 1/23/16.
 */
public class EnemyTile extends BoardElement {

    private int enemyPosX = 1;
    private int enemyPosY = 1;

    public EnemyTile(){
        this.setBackground(Color.LIGHT_GRAY);
    }

    public int moveTo() {
        return 0;
    }


    public String print() {
        return "E";
    }

    public void setEnemyPosX(int a){
        this.enemyPosX = a;
    }

    public void setEnemyPosY(int a){
        this.enemyPosY = a;
    }

    public int getEnemyPosX() {
        return enemyPosX;
    }

    public int getEnemyPosY() {
        return enemyPosY;
    }
}
