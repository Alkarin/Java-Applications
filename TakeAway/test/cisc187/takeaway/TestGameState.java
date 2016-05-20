package cisc187.takeaway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class TestGameState {

  private GameState g;

  @Test
  public void verifyPilesAtStart() {
    g = new GameState(3, 5);
    assertEquals("Failed to init # of piles to 3", 3, g.getCoins().size());
  }
  @Test
  public void verifyCoinsAtStart() {
    g = new GameState(3, 5);
    for (Integer i : g.getCoins()) {
      assertEquals("Failed to init # of coins per pile to 5", 5, (int) i);
    }
  }

  @Test
  public void verifyRandomPilesAtStart() {
    for (int i=0; i<100; i++) {
      g = new GameState();
      assertTrue("Number of piles is invalid!", 
          (g.getCoins().size() > 0 && g.getCoins().size() <= 9));
    }

  }

  @Test
  public void verifyRandomCoinsAtStart() {
    for (int i=0; i<100; i++) {
      g = new GameState();
      for (Integer j : g.getCoins()) {
        assertTrue("Number of coins is invalid", (j >= 3 && j <= 13));
      }

    }

  }

  @Test
  public void get1PileOfCoins() {
    g = new GameState(3, 7);
    for (int i = 0; i < g.getCoins().size(); i++) {
      assertEquals("Failed to get 7 coins from pile " + i, 7, g.getCoinsAt(i+1));
    }
  }


}
