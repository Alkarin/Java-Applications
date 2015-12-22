
/**
 * Element to create the player position
 * 
 * @author Alexander Vaughan (5448832)
 * @version 12/3/2015
 */
import java.awt.*;
import javax.swing.*;
public class PlayerElement extends BoardElement
{
    /**
     * playerPosX and playerPosY variables declared
     * ensures playerElement starts at (0,0)
     */
    private int playerPosX = 0;
    private int playerPosY = 0;
    public PlayerElement(){
        this.setBackground(Color.CYAN);
    }
    /**
     * Method to move PlayerElement
     * Not used at the moment
     */
    public int moveTo(){
        return 0;
    }
    
    /**
     * Method that moves player position based on Snake and Ladder Element values
     */
    public void jumpTo(int steps){
        //if statement to move player accordingly based on Element values
        if(steps > 0){
            playerPosX += steps;
            //if statement for when player position would go past board size
            if(playerPosX > Board.BOARD_SIZE -1 && playerPosY < Board.BOARD_SIZE -1){
                playerPosX -= Board.BOARD_SIZE;
                //moves player to the next Y level
                playerPosY++;
            }else if(playerPosY > Board.BOARD_SIZE -1){
                //else if PlayerPosY tries to go past board size
                playerPosY = Board.BOARD_SIZE - 1;
                    
            } else if(playerPosX > Board.BOARD_SIZE -1){
                //'Catches'/does not call any other statements for this condition
                //specifically for when PosX>Board.BOARD_SIZE -1 && PosY == BOARD_SIZE -1
            }
        } else {
            //if statement for when element with value under 0 is landed on
            if(steps < 0){
                playerPosX += steps;
                if(playerPosX < 0){
                    //moves player down to the lower posY level when PosX goes below 0
                    playerPosX += Board.BOARD_SIZE;
                    playerPosY--;
                    //if statement, keeping PosY inside the BOARD_SIZE 
                    if(playerPosY < 0){
                        playerPosY = 0;
                    }
                }
            }
        }
    }
    
    /**
     * Method to print out for the PlayerElement
     */
    public String print(){
        return "X";
    }
    
    /**
     * Method to
     */
    public void setPosX(int a){
        this.playerPosX = a;
    }
    
    
    /**
     * Method to 
     */
    public void setPosY(int a){
        this.playerPosY = a;
    }
    
    
    /**
     * Method to return playerPosX
     */
    public int getPosX(){
        return playerPosX;
    }
    
    /**
     * Method to return playerPosY
     */
    public int getPosY(){
        return playerPosY;
    }
}
