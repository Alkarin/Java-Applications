package cisc187.takeaway;

import java.util.*;

/**
 * Defines the actions taken by a computer player of the game.
 * This class needs work, but generally plays a rational game.
 */

public class ComputerPlayer {

  /**
   * The heap that the computer chooses to play.
   */
  private int heap;   // 

  /**
   * The number of items the computer chooses to remove from the heap
   */
  private int take;
  private GameState game;

  /**
   * Init the computer player
   * @param g the game to be evaluated by the computer
   */
  public ComputerPlayer(GameState g) {
    this.game = g;
  }

  /**
   * Evaluate the entire state of the supplied game.
   * <p>
   * The result of the evaluation is new values for heap and take
   * </p>
   */
  void evaluatePosition() {
    this.heap = 0;
    this.take = 0;

    switch (this.game.getCoins().size()) {
      case 1: this.heap = 1;
              this.take = this.game.getCoinsAt(0);
              break;
      case 2: evaluateEnd();
              break;
      default: evaluateMid();
               break;
    }
  }


  /**
   * Evaluate the begining and middle state of a game.
   * <p>
   * The result of the evaluation is new values for {@link #heap} and {@link #take}
   * </p>
   */
  private void evaluateMid() {
    int sum = 0;   // nim sum of all heaps
    int tmp = 0;
    int index = 1;

    for (Integer i : this.game.getCoins()) {
      // compute binary sum of each heap
      sum = sum ^ i;
    }

    for (Integer i : this.game.getCoins()) {
      tmp = sum ^ i;
        //System.out.println("sum " + sum + " tmp: " + tmp + " i: " + i);
      if (tmp < i) {
        this.take = i - tmp;
        this.heap = index;
        //System.out.println("maybe take " + this.take + " from heap: " + this.heap);
      }
      index++;
    }
  }

  /**
   * Evaluate the special case when we have only 2 piles: <br/>
   * The winning strategy is to ensure at the end of your turn, both piles are equal
   * <p>
   * The result of the evaluation is new values for heap and take
   * </p>
   */
  private void evaluateEnd() {
      int sum = this.game.getCoinsAt(0) - this.game.getCoinsAt(1);
      this.heap = 1;
      if (sum == 0) {
        this.take = 1;
      } else if (sum > 0) {
        this.take = sum;
      } else {
        this.heap = 2;
        this.take = -sum;
      }

  }

  /**
   * Respond to the game query "Which heap?" to choose from.
   * @return The heap finally chosen by the computer
   * <p>
   * If the evaluator was unable to choose, then the largest heap is chosen.
   * </p>
   */
  int chooseHeap() {
    int heap = -1;  // biggest heap
    if (this.heap < 1) {
      // we did not find a winning position
      for (Integer i : this.game.getCoins()) {
        heap = Math.max(heap, i);

      }
    } else {
      heap = this.heap;
    }
    return heap -1;
  }

  /**
   * Respond to the game query "How many?" coins to pick.
   * @return The number of coins finally chosen by the computer
   * <p>
   * If the evaluator was unable to choose, then 1 coin will be chosen.
   * </p>
   */
  int howMany() {
    int howMany = 1;
    if (this.take > 0) {
      howMany = this.take;
    }
    return howMany;
  }

}


