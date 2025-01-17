import java.util.*;

public class MyProgram {

  static final int ROUNDS = 200;

  static final int VERBOSE = 2;

  public static void main(String[] args)
  {
    ArrayList<Player> p = new ArrayList<Player>();

    //p.add(new Player("Random 1", ROUNDS));
    //p.add(new Player("Random 2", ROUNDS));
    p.add(new AdaptivePlay("AdaptivePlay", ROUNDS));
    p.add(new FourToSix("FourToSix", ROUNDS));
    
    ArrayList<Integer> s = new ArrayList<Integer>();
    for (int i=0; i<p.size(); i++)
      s.add(-1);

    System.out.println();

    int numMatches = 0;

    // -- Round robin
    for (int i=0; i<p.size(); i++) {
      for (int j=i+1; j<p.size(); j++) {
        p.get(i).clear();
        p.get(j).clear();
        numMatches += 1;

        for (int round=0; round<ROUNDS; round+=1) {
          s.set(i, p.get(i).play());
          s.set(j, p.get(j).play());
          p.get(i).update(s.get(i), s.get(j));
          p.get(j).update(s.get(j), s.get(i));
          if (VERBOSE > 1) {
            System.out.println(p.get(i).getName() + ": " + s.get(i) + "\t" +
            p.get(j) + ": " + s.get(j) + "\t" +
            p.get(i) + " score=" + p.get(i).getScore()+ "\t" +
            p.get(j) + " score=" + p.get(j).getScore());
          }
        }
        if (VERBOSE > 0) {
          System.out.println("After " + ROUNDS + " rounds: " +
          p.get(i) + " score=" + p.get(i).getScore() + ", " +
          p.get(j) + " score=" + p.get(j).getScore());
        }
      }
    }

    System.out.println("----------------------------------------------Final scores!");

    // Create a new list, q, that sorts players in grand-score order
    ArrayList<Player> q = new ArrayList<Player>();
    for (int i=0; i<p.size(); i++) {
      int j=0;
      while (j<q.size() && q.get(j).getCumulativeScore() > p.get(i).getCumulativeScore()) {
        j++;
      }
      q.add(j, p.get(i));
    }

    for (int j=0; j<q.size(); j++) {
      System.out.printf("%20s : %.2f\n",
        q.get(j), q.get(j).getCumulativeScore()/(double)(ROUNDS * (p.size()-1)));
    }

  }
}