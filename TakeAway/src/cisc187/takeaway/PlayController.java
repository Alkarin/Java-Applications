package cisc187.takeaway;

import java.util.*;

/**
 * @author Alexander Vaughan(5448832)
 * @version 1.0
 * @since 02-20-2016
 *
 * The game play controller, which manages players, accepts user input,
 * keeps track of whose turn it is,
 * and declares winners.
 */

public class PlayController {

    /**
     * Player1's name
     */
	private static String player1;
    /**
     * Player2's name, will be predefined if playing against an AI
     */
	static String player2;
    /**
     * Boolean to keep track of which players turn it is.
     */
	static transient boolean playerTurn;
    /**
     * The Gamestate used for the session
     */
	private GameState g;
    /**
     * The AI
     */
    private ComputerPlayer ai;
    /**
     * Boolean state that defines if there is a current winner. Game will continue to run until victor is true.
     */
    private static boolean victor;
    /**
     * The heap the player chooses
     */
    private int h;
    /**
     * The amount of coins from a heap the player chooses
     */
    private int c;
    /**
     * Scanner to receive user input
     */
	public static Scanner input = new Scanner(System.in);

    /**
     * Constructor that sets up players names, an AI if applicable and creates a GameState
     * @param hasAI     boolean value for if the game has an AI player
     * @param isRandom  boolean value for if the game will be random or piles/coins are user defined
     */
	public PlayController(boolean hasAI, boolean isRandom){

        //initializing new game by setting no winner and ensuring Player1 starts the game
        playerTurn = true;
        victor = false;

        //Asking user for player1 name
        System.out.println("Please enter player 1's name");

        player1 = input.nextLine();

        //Creating AI when applicable
        if(hasAI){
            ai = new ComputerPlayer(g);
            player2 = "HAL9000";
        } else {
            System.out.println("Please enter player 2's name");
            player2 = input.nextLine();
        }

        //Creating GameState as defined by user
        if(isRandom){
            //Initializing random GameState
            g = new GameState();
        } else{
            //Piles and coins generated per user input
            System.out.println("Please enter the number of piles");
            int piles = input.nextInt();
            System.out.println("Please enter the number of coins");
            int coins = input.nextInt();
            System.out.println("Generating game with " + piles + " piles of " + coins + " coins...");
            g = new GameState(piles, coins);
        }
    }

    /**
     * Checks and declares when and if there is a winner
     */
	private void declareWinner(){

        //Checks if last move won the game
        if(GameState.getCoins().size() > 0){
            victor = false;
        } else {
            //declares winner
            promptPlayer();
            System.out.println("has won the game!");
            victor = true;
        }
	}

    /**
     * Method that prompts current player for input of which head to select
     * @return i    The heap selected by the player
     */
	private int getHeap(){
        //Asking user for heap selection input
        promptPlayer();
        System.out.println("please enter which heap to take away from");
        int i = input.nextInt();

        //User input validation
        while (i > GameState.getCoins().size()) {
            System.out.println("Must enter an existing heap");
            i = input.nextInt();
        }
        //returns user input
        return i;
	}

    /**
     * Method that prompts user how many coins they wish to take from a given pile
     * @return c    The amount of coins selected by the player
     */
	private int howManyCoins(){

        //boolean state for user input validation
        boolean flag = false;

        //For testing
        //System.out.println(GameState.getCoins());

        //Prompting user for input
        promptPlayer();
        System.out.println("how many coins would you like to take?");
        int c = input.nextInt();
        //Specified coins pile index
        int k = GameState.getCoinsAt(h-1);

        //input validation
        do {
            //Checks if user is picking an existing amount of coins from specified coins pile index
            if (c < 1) {
                System.out.println("You must pick at least one coin");
                c = input.nextInt();
            } else if (c == k){
                flag = true;
            } else if(c > k) {
                System.out.println("There's not that many coins!");
                c = input.nextInt();
            } else {
                flag = true;
            }
        } while(flag == false);
        return c;
	}

    /**
     * Method which structures the game play. Keeps track of who's turn it is
     * and offers appropriate input methods depending on if player is human or not.
     * States AI input to user
     */
    private void play(){

        //Executes as long as game has no winner
        do {
            if (playerTurn == true) {
                //Human player1 input

                //Prompts user for heap and coin selection
                h = getHeap();
                c = howManyCoins();

                //Executes player move
                GameState.removeCoinsAt(h, c);
                //Redisplays GameState
                GameState.printGameState();
                //Checks if won game on last move
                declareWinner();

                //Ends player turn
                playerTurn = false;
            } else if (player2 == "HAL9000") {

                ai.evaluatePosition();

                //For Testing
                //System.out.println("In HAL");
                //System.out.println("c: " + c + " h: " + h );

                //Prompts user for heap and coin selection
                h = ai.chooseHeap();
                c = ai.howMany();

                //AI heap selection validation
                for(int i = 0; h > GameState.getCoins().size(); i++){
                    h--;
                    i++;
                }
                if(h <= 0) {
                    h = 1;
                }
                if (c > GameState.getCoinsAt(h-1)){
                    c = GameState.getCoinsAt(h-1);
                }
                for(int i = 0; c > GameState.getCoinsAt(h-1); i++){
                    c = GameState.getCoinsAt(h-1);
                }

                //Executes player move
                GameState.removeCoinsAt(h, c);
                promptPlayer();
                //Declares AI moves to human players
                System.out.print("took " + c + " coins from pile " + h +"\n");
                //Redisplays GameState
                GameState.printGameState();
                //Checks if won game on last move
                declareWinner();

                //Ends player turn
                playerTurn = true;
            } else {
                //Human player2 input

                //Prompts user for heap and coin selection
                h = getHeap();
                c = howManyCoins();
                //Executes player move
                GameState.removeCoinsAt(h, c);
                GameState.printGameState();
                //Checks if won game on last move
                declareWinner();

                //Ends player turn
                playerTurn = true;
            }
        } while(victor == false);
	}

    /**
     * Method that prompts the appropriate player depending on who's turn it is
     */
	private void promptPlayer(){

        //Checks playerTurn
        if(playerTurn == true){
            //Prints appropriate player name
            System.out.print(player1 + ", ");

        } else {
            //Prints appropriate player name
            System.out.print(player2 + ", ");
        }
	}

    /**
     * Main method
     *@param args   hasAI - boolean for game AI creation, isRandom - boolean for game is Random or user defined
     *
     * <p>Creates a new game based on args, and plays the game</p>
     */
	public static void main(String[] args) {
		PlayController controller = new PlayController(true, true);
		controller.play();
	}
}
