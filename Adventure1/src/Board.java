/**
 * Created by alkarin on 1/23/16.
 */
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;



public class Board {
    public final static int BOARD_SIZE = 25;
    private final static Random rand = new Random();
    private BoardElement[][] boe = new BoardElement[BOARD_SIZE][BOARD_SIZE];
    private JTextField nameField;
    private JPanel boardArea;
    private PlayerTile player1 = new PlayerTile();
    private EnemyTile enemy1 = new EnemyTile();
    public static JFrame mainframe;

    //layout for boardArea
    private GridLayout boardLayout = new GridLayout(BOARD_SIZE,BOARD_SIZE,0,0);

    /**
     * Constructor Method to build Board class
     */
    public Board(){
        initialise();
        initialiseGui();
    }

    /**
     * Method to build Swing Gui
     */
    public void initialiseGui(){

        mainframe = new JFrame();

        //setting size, viewability, and terminating ability to JFrame
        mainframe.setVisible(true);
        mainframe.setSize(750,600);
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainframe.setTitle("Adventure!");

        //creating JPanels for Layout
        JPanel panel = new JPanel();
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelCenter = new JPanel();

        //creating border1
        TitledBorder border1;
        border1 = BorderFactory.createTitledBorder("");

        //adding title border to panels
        panelNorth.setBorder(border1);
        panelSouth.setBorder(border1);
        panelEast.setBorder(border1);
        panelWest.setBorder(border1);
        panelCenter.setBorder(border1);

        //setting panel layouts
        panel.setLayout(new BorderLayout());

        //Adding JComponents
        JLabel playerInfo = new JLabel("Character");
        panelEast.add(playerInfo);

        JButton test = new JButton("Test");
        panelWest.add(test);

        JMenuBar menuBar = new JMenuBar();
        mainframe.setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        JMenu help = new JMenu("Help");
        menuBar.add(help);

        JMenuItem about = new JMenuItem("About");
        help.add(about);

        //ActionListeners
        class exitAction implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        }

        class aboutAction implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                aboutAction();
            }
        }

        //adding ActionListeners to JButtons/JMenus
        exit.addActionListener(new exitAction());
        about.addActionListener(new aboutAction());



        boardArea = new JPanel();
        panelCenter.add(boardArea);
        boardArea.setLayout(boardLayout);

        //adding panels to the main panel
        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(panelSouth, BorderLayout.SOUTH);
        panel.add(panelEast, BorderLayout.EAST);
        panel.add(panelWest, BorderLayout.WEST);
        panel.add(panelCenter, BorderLayout.CENTER);


        //adding the main panel to initaliseGui() to be used on call
        mainframe.add(panel);

    }

    /**
     * Initialiser Method to place Board Elements into boe array for Board
     */
    public void initialise(){

        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                boe[i][j] = new PassTile();
                //ADD WallTile ASWELL, Board is only creating PassTiles atm
            }

            //if(i == 0){
                //boe[i][getRandomColumn()] = new PassTile();
            //}
            //else{
                //boe[i][getRandomColumn()] = new PassTile();
            //}
        }
        boe[3][4] = new WallTile();

    }

    /**
     * Method to print out the Board with proper Elements
     */
    public void display(){

        boardArea.removeAll();

        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                if(i == player1.getPlayerPosX() && j == player1.getPlayerPosY()){
                    boardArea.add(player1);
                }
                else if(i == enemy1.getEnemyPosX() && j == enemy1.getEnemyPosY() ){
                    boardArea.add(enemy1);
                }
                else {
                    boardArea.add(boe[i][j]);
                }
            }
        }

        boardArea.updateUI();
    }

    public static int getRandomColumn(){

        return rand.nextInt(BOARD_SIZE);
    }

    /**
     * Main method to create the Board Object, and to print it out in the Gui
     */
    public static void main(String[] args) {
        Board board = new Board();
        board.display();
        mainframe.validate();

    }

    /**
     * ActionListener Methods
     */

    public void exitAction(){
        System.exit(0);
    }


    public void aboutAction(){
        JFrame about = new JFrame();
        about.setVisible(true);
        about.setSize(250,250);
        //about.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea helpText = new JTextArea("Stuff");

        JPanel panel = new JPanel();
        about.add(panel);

        panel.add(helpText);
    }

}
