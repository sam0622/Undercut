import java.util.*;

public class FourToSix extends Player {
  int c = 0;
  int roundsPlayed = 0;
  int[] p = {7, 4, 2, 7};
  int[] p2 = {3, 5, 1, 6};
  public FourToSix(String name, int rounds) {
    super(name, rounds);
  }


  /**
   *  play() returns the pattern 4 5 6 5 4 5 6 5 4...
   */
  public int play() {
    roundsPlayed++;
    if (this.c > p.length - 2 || this.roundsPlayed == 200) {this.c = 0;}

    if (this.roundsPlayed > 100 && this.roundsPlayed < 200) {
      c++;
      return p[c];
    } else if (this.roundsPlayed >= 200) {
      c++;
      return p2[c];
    }
    if (index < 2) { return 4; }
    else if (myHistory[index-1] == 4) { return 5; }
    else if (myHistory[index-1] == 5) {
        if (myHistory[index-2] == 4) { return 6; }
        else { return 4; }
    }
    else if (myHistory[index-1] == 6) { return 5; }
    else { return 4; }
  }

}