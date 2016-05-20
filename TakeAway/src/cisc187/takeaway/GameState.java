package cisc187.takeaway;

import java.util.*;

/**
 * @author Alexander Vaughan(5448832)
 * @version 1.0
 * @since 02-20-2016
 *
 * This class defines the information needed to play the game.
 * This includes how many piles of coins there are and how many coins are in each pile,
 * along with the constructors to create these piles
 */

public class GameState {

    /**
     * The games state is mainly defined by how many piles there are
     * and how many coins are in each of these piles. To demonstrate this I
     * have used an Arraylist, with the size() of elements within the Arraylist to represent
     * the amount of piles, while the values of each of these elements to represent
     * the amount of coins in said pile
     */
    private static List<Integer> coins = new ArrayList<>();
    /**
     * Random generator
     *
     * <p>This class has constructors capable of creating a 'random' environment for
     * the game of Take away, allowing a random number of piles of coins to be generated
     * within a certain range.</p>
     */
	private static Random rand = new Random();


    /**
     * The no args constructor, is used  for  generating a 'random' game
     * environment of a randomly decided amount of piles of coins from
     * 2 to 6 piles of 4 to 10 coins
     */

	 GameState(){
         //generates the number of piles
		int piles = rand.nextInt(4) + 2;
         //generates the number of coins to be placed in piles
		int numCoins = rand.nextInt(6)+ 4;

         //letting user know the current game environment
		System.out.println("Generating game with "+ piles + " piles of " + numCoins + " coins...");

         //creates initial game board
		for(int i = 0; i < piles; i++){
			int k = i+1;
			System.out.print("Pile " + k + ": ");
            coins.add(numCoins);

			for(int j = 0; j < numCoins; j++){
				System.out.print("*");
			}
			System.out.println();
		}
        //for testing
		//System.out.println(coins.size());
	}

    /**
     * GameState() with args creates the game environment with the specified piles and coins
     * defined by the user.
     *
     * @param piles     user input for initial number of piles
     * @param numCoins  user input for initial number of coins for each pile to start with
     */
	GameState(int piles, int numCoins){

        //creates initial game board per user defined args
        for(int i = 0; i < piles; i++){
			int k = i+1;
			System.out.print("Pile " + k + ": ");
            coins.add(numCoins);

			for(int j = 0; j < numCoins; j++){
			System.out.print("*");
			}
			System.out.println();
		}
        //for testing
        //System.out.println(coins);
	}

    /**
     * Returns the essential GameState
     * @return coins    returns ArrayList of current piles and their coins
     */
	static List<Integer> getCoins(){
		return  coins;
	}

    /**
     * Returns the number of coins of the specified element
     * @param index     the specified element
     * @return int i    the value from index
     */
	static int getCoinsAt(int index){
        int i = coins.get(index);
		return i;
	}

    /**
     * Method that changes the values within coins
     * and removes the pile element if it has reached zero coins
     * @param index     The specific element to change
     * @param numCoins  The amount of coins to be removed
     */
	static void removeCoinsAt(int index, int numCoins){
        //subtracts the specified coins index's value by the amount of coins
        coins.set(index-1, coins.get(index-1) - numCoins);

        //removal of pile element when it no longer has any coins
        for(int i = 0; i < coins.size(); i ++){
            int val = coins.get(i);
            if (val == 0) {
                coins.remove(i);
            }
        }
    }

    /**
     * Method that prints the visual GameStates piles and coins within them
     */
    static void printGameState(){

        for(int i = 0; i <= coins.size()-1; i++){
            int k = i+1;

            System.out.print("Pile " + k + ": ");


            for(int j = 0; j < coins.get(i); j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}


