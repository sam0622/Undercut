import java.util.*;

public class Player {

  protected String name;

  // my (the player's) history of moves, and
  // op (the opponent's) history of moves.
  protected int[] myHistory;
  protected int[] opHistory;
  protected int index;

  // my (the player's) score and op (the opponent's) score
  private int myScore;
  private int opScore;
  private int myCumulativeScore;

  public Player(String name, int rounds) {
    this.name = name;
    this.myHistory = new int[rounds];
    this.opHistory = new int[rounds];
    this.index = 0;
    this.myScore = 0;
    this.opScore = 0;
    this.myCumulativeScore = 0;
  }

  public void clear() {
    this.myHistory = new int[this.myHistory.length];
    this.opHistory = new int[this.opHistory.length];
    this.index = 0;
    this.myScore = 0;
    this.opScore = 0;
  }
  
  public String getName() { return name; }
  public int getScore() { return myScore; }
  public int getCumulativeScore() { return myCumulativeScore; }

  private void incrMyScore(int score) {
    this.myScore += score;
    this.myCumulativeScore += score;
  }

  private void incrOpScore(int score) {
    this.opScore += score;
  }

  /**
   *  play() returns a random value in 1..7 (default)
   */
  public int play() {
    // default: return a random value 1..7
    return (int)(Math.random() * 7) + 1;
  }

  /**
   *  update() is used by the harness to update myHistory
   *  and opHistory
   */
  public void update(int mySelection, int opSelection) {
    if ((mySelection < 1) || (mySelection > 7)) {
      System.err.println("update: mySelection = " +mySelection+ "! Should be in range 1..7. Cannot continue.");
      System.exit(-1);
    } else if ((opSelection < 1) || (opSelection > 7)) {
      System.err.println("update: opSelection = " +opSelection+ "! Should be in range 1..7. Cannot continue.");
      System.exit(-1);
    } else {

      myHistory[index] = mySelection;
      opHistory[index] = opSelection;
      index += 1;

      if (mySelection - opSelection == 1) {
        incrOpScore(mySelection + opSelection);
      } else if (opSelection - mySelection == 1) {
        incrMyScore(mySelection + opSelection);
      } else {
        incrOpScore(opSelection);
        incrMyScore(mySelection);
      }
    }
  }

  public String toString() {
      return this.name;
  }

}