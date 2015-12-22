
/**
 * Board class for Snakes and Ladders game. Constructs, initialises, and creates a Board Object
 * to be printed out for user to see.
 * 
 * @author Alexander Vaughan (5448832)
 * @version 12/17/2015
 */
//importing Random class to create getRandomColumn method and for rand.nextBoolean in initialise method
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;
public class Board extends JFrame
{
    /**
     * BOARD_SIZE is final max size of the board row/columns
     */
    public final static int BOARD_SIZE = 10;
    /**
     * rand to be used in boolean and column random generation
     */
    private final static Random rand = new Random();
    /**
     * boe actual board element to create game board
     */
    private BoardElement[][] boe = new BoardElement[BOARD_SIZE][BOARD_SIZE];
    /**
     * nameField for the JFrame
     */
    private JTextField nameField;
    /**
     * boardArea location where the board will be displayed
     */
    private JPanel boardArea;
    /**
     * Player1 is the playerElement
     */
    private PlayerElement player1 = new PlayerElement();
    /**
     * creating a specific layout to space out all of the board elements
     */
    private GridLayout boardLayout = new GridLayout(BOARD_SIZE,BOARD_SIZE,0,5);
    /**
     * JTextfield named throwfield, which will be used to place the dieRoll
     */
    private JTextField throwField = new JTextField("",1);
    /**
     * class variable for dieRoll int
     */
    private int dieRoll;
    
    /**
     * Contructor method for Board class
     */
    public Board(){
        initialise();
        initialiseGui();
        String name = JOptionPane.showInputDialog("Please enter name");
        nameField.setText(name);
    }
    /**
     * Method to build Swing Gui 
     */
    public void initialiseGui(){
        //Setting the size and viewability of frame
        this.setVisible(true);
        this.setSize(575,520);
        this.setTitle("Eels and Escalators");
        
        //creating various fonts to use
        Font title1 = new Font("Serif", Font.BOLD | Font.ITALIC, 20);
        Font f = new Font("Serif Italic", Font.BOLD, 12);
        Font g = new Font("SansSerif Bold", Font.BOLD, 12);
        
        //Creating all necessary panels for specified layout. 
        //Using compass terminology for heirarchy and location definition
        JPanel panel = new JPanel();
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelWestNorth = new JPanel();
        JPanel panelWestCenter = new JPanel();
        JPanel panelEastNorth = new JPanel();
        JPanel panelEastCenter = new JPanel();
        
        //creating title variable to assign a border to
        TitledBorder title;
        title = BorderFactory.createTitledBorder("");
        
        //using the created title border on all main panels
        panelNorth.setBorder(title);
        panelEast.setBorder(title);
        panelSouth.setBorder(title);
        panelWest.setBorder(title);
        panelCenter.setBorder(title);
        
        //setting layout for panel and west and east panels, to create specified look
        panel.setLayout(new BorderLayout());
        panelWest.setLayout(new BorderLayout());
        panelEast.setLayout(new BorderLayout());
        
        //Creating labels and textfields and adding them to their appopriate panels
        JLabel gameLabel = new JLabel("A Game of Ladders and Snakes");
        panelNorth.add(gameLabel);
        
        JLabel nameLabel = new JLabel("Username");
        panelWestNorth.add(nameLabel);
        
        nameField = new JTextField("",10);
        panelWestCenter.add(nameField);
        //ensures JTextField is not editable by user
        nameField.setEditable(false);
        
        JLabel diceLabel = new JLabel("Last throw");
        panelEastNorth.add(diceLabel);
        
        //throwField declared in class variables
        panelEastCenter.add(throwField);
        //ensures JTextField is not editable by user
        throwField.setEditable(false);
        
        JButton diceButton = new JButton ("Roll");
        panelSouth.add(diceButton);
        
        //creating additional JPanel to put our game board in
        //then adding it to the center panel
        //and giving it our specifified boardlayout declared in our class variables
        boardArea = new JPanel();
        panelCenter.add(boardArea);
        boardArea.setLayout(boardLayout);
        
        //adding panels to main panels
        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(panelSouth, BorderLayout.SOUTH);
        panel.add(panelEast, BorderLayout.EAST);
        panel.add(panelWest, BorderLayout.WEST);
        panel.add(panelCenter, BorderLayout.CENTER);
        panelWest.add(panelWestNorth, BorderLayout.NORTH);
        panelWest.add(panelWestCenter, BorderLayout.CENTER);
        panelEast.add(panelEastNorth, BorderLayout.NORTH);
        panelEast.add(panelEastCenter, BorderLayout.CENTER);
        
        //assigning previously created fonts to JLabels
        nameLabel.setFont(f);
        gameLabel.setFont(title1);
        diceLabel.setFont(g);
        nameLabel.setForeground(Color.BLUE);
        gameLabel.setForeground(Color.RED);
        diceLabel.setForeground(Color.MAGENTA);
        
        //adding the main panel to initaliseGui() to be used on call
        this.add(panel);
        
        //creating an anonymous class for the ActionListener
        diceButton.addActionListener(
            new ActionListener()
            {
                //calling the dice() method whenever diceButton is clicked
                public void actionPerformed(ActionEvent e){
                    rollDiceAndMovePlayer();
                    //throwField.setText(Integer.toString(dice()));
                }
            }
        );
    }
    
    /**
     * Initialiser method to place board elements into boe array for Board
     */
    public void initialise(){
        //Nested for loop to place elements in both rows and columns of boe array
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                //for every boe index, creating a new Regular element to be placed there
                boe[i][j] = new RegularElement();
            }
            //if statement to add non-regular elements to board
            //rand.nextBoolean to randomly choose between Snake or Ladder element to be placed
            if(i == 0){
                //ensures only a ladder element can be placed on bottom row
                boe[i][getRandomColumn()] = new LadderElement();
            }else if(i == BOARD_SIZE -1){
                //if statement to ensure very last board element(game winner) is a regular element
                if(boe[i][getRandomColumn()] == boe[i][9]){
                    boe[i][9] = new RegularElement();
                }
                //ensures only a snake element can be placed on the top row
                boe[i][getRandomColumn()] = new SnakeElement();
            }else{
                //if statement to add non-regular elements to board for all other rows
                if(rand.nextBoolean()){
                    //places Snake element in a random column of the boe[i] row
                    boe[i][getRandomColumn()] = new SnakeElement();
                }
                else{
                    //places Ladder element in a random column of the boe[i] row
                    boe[i][getRandomColumn()] = new LadderElement();
                }
            }
        }
    }
    
    /**
     * Method when called, will roll the dice and move the player Element accordingly to the int created from setDice()
     */
    public void rollDiceAndMovePlayer(){
        //rolling the dice
        setDice();
        //sets setDice() int to throwField in GUI
        throwField.setText(String.valueOf(getDice()));
        player1.jumpTo(getDice());
        display();
        //if statements for player posY/PosX conditionals
        if(player1.getPosX() > BOARD_SIZE -1 && player1.getPosY() == BOARD_SIZE -1 ){
            //player can only roll winning number or lower
            //if player position goes off the board on the top row, it reverts the getDice roll and notifies player
            player1.jumpTo(-getDice());
            display();
            JOptionPane.showMessageDialog(null, "You did not roll a winning number, try again.");
        } else if(player1.getPosX() == BOARD_SIZE -1 && player1.getPosY() == BOARD_SIZE -1 ){
            //Notifies player has gotten to the last board element and has won the game
            JOptionPane.showMessageDialog(null, "You have won the game! Good Job!");
            display();
            //JOptionPane to ask player if they wish to play again
            Object[] options = { "Yes", "No Thanks"};
            int input = JOptionPane.showOptionDialog(null, "Would you like to play again?","Congratulations!",JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            //code execute based on players selection
            if(input == JOptionPane.OK_OPTION){
                //Resets game to play again
                player1.setPosX(0);
                player1.setPosY(0);
                initialise();
                display();
                throwField.setText("");
            } else {
                //exits program on user request
                JOptionPane.showMessageDialog(null,"See you again!");
                System.exit(0);
            }
        }else{
            //assigning current player position to element
            BoardElement element = boe[player1.getPosY()][player1.getPosX()];
            //if statement conditionals for if the player lands on a ladder or snake element
            if(element.moveTo() > 0){
                try{
                    Thread.sleep(200);
                    JOptionPane.showMessageDialog(null, "You landed on a Ladder!");
                }catch(InterruptedException e){
                     //ignoreException
                }
                player1.jumpTo(element.moveTo());
                display();
                element = boe[player1.getPosY()][player1.getPosX()];
            } else { 
                if(element.moveTo() < 0){
                    try{
                         Thread.sleep(200);
                         JOptionPane.showMessageDialog(null, "You landed on a Snake!");
                    }catch(InterruptedException e){
                         //ignoreException
                    }
                    player1.jumpTo(element.moveTo());
                    display();
                    element = boe[player1.getPosY()][player1.getPosX()];   
                }
            }          
        }
    }
    
    /**
     * Method to roll a 6 sided dice
     */
    public void setDice(){
        dieRoll = rand.nextInt(6)+1;
    }
    
    /**
     * Method to return setDice() random int dieRoll
     */
    public int getDice(){
        return dieRoll;
    }
    
    /**
     * Method to print out the 10x10 board with its Elements
     */
    public void display(){
        //removes board to be redrawn when called. So boards do not overlap
        boardArea.removeAll();
        //Nested for loop to print out all board elements
        for(int i = BOARD_SIZE-1; i >= 0 ; i--){
            for(int j = 0; j < BOARD_SIZE ; j++){
                if(i == player1.getPosY() && j == player1.getPosX()){
                    //if the position is the same position as Player1, it will print out player 'Icon'
                    boardArea.add(player1); 
                }
                
                else{
                    //Otherwise, normally it will print out the appropriate boardElements
                    boardArea.add(boe[i][j]);
                }
            }
        }
        //updates the boards UI, ensuring it is correctly displayed
        boardArea.updateUI();
    }

    /**
     * Method to select a random Column within a board row
     */
    public static int getRandomColumn(){
        //using BOARD_SIZE to avoid any possible out of bounds exceptions
        return rand.nextInt(BOARD_SIZE);
    }
    
    /**
     * Main method, to create a Board object and to print it out
     */
    
    public static void main(String[] args){
        //try catch to ensure program will run without crashing
        try{
            Board board = new Board();
            board.display();
            board.validate();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "An exception has been found: " + e.getMessage());
        }
    }
}





