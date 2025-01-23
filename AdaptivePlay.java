import java.util.Random;
import java.util.Arrays;

public class AdaptivePlay extends Player {
    Random rand = new Random();
    private int[] identifiedPattern;
    private int[] trimmedOpHistory;
    private int roundsPlayed;
    public AdaptivePlay(String name, int rounds) {
        super(name, rounds);
        this.identifiedPattern = null;
        this.trimmedOpHistory = null;
        this.roundsPlayed = 0;
    }

    /**
     * Resets instance variables to default values
     **/
    @Override
    public void clear() {
        super.clear();
        this.identifiedPattern = null;
        this.trimmedOpHistory = null;
        this.roundsPlayed = 0;
    }

    /**
     * Search for patterns and then play a number accordingly
     *
     * @return the played number
     */
    @Override
    public int play() {
        int play = calculateIdealPlay();
        if (this.roundsPlayed > 1) {
            this.trimmedOpHistory = Arrays.copyOfRange(this.opHistory, 1, this.roundsPlayed);
            this.identifiedPattern = patternSearch(this.trimmedOpHistory);
            if (this.identifiedPattern == null) {
                for (int i = 1; i < this.roundsPlayed; i++) {
                    this.trimmedOpHistory = Arrays.copyOfRange(opHistory, i, this.roundsPlayed);
                    this.identifiedPattern = patternSearch(this.trimmedOpHistory);
                    if (this.identifiedPattern != null) {
                        break;
                    }

                }

            }
        }
        roundsPlayed++;
        return play;
    }

    /**
     * If a pattern is identified, it plays the expected, minus 1. Returns random from 4 to 6 otherwise
     *
     * @return the number to be played
     */
    private int calculateIdealPlay() {
        if (this.identifiedPattern == null || this.roundsPlayed < 3) {
            return rand.nextInt(4, 8);
        }

        int i = (this.roundsPlayed - 1) % this.identifiedPattern.length;
        int r = this.identifiedPattern[i];
        return r - 1;
    }

    /**
     * Uses a brute-force pattern matching algorithm to find patterns
     *
     * @param arr The array to search
     * @return the pattern identified, will return null if no patterns are found
     * <p>
     * Precondition: arr is not null
     * </p>
     **/
    private int[] patternSearch(int[] arr) {
        int n = arr.length;

        // The shortest possible pattern length would be one
        // The longest possible pattern length would be half of the length
        for (int patternLength = 1; patternLength <= n / 2; patternLength++) {
            boolean isValid = true;
            for (int j = 0; j < n - patternLength; j++) {
                if (arr[j] != arr[j + patternLength]) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                return Arrays.copyOfRange(arr, 0, patternLength);
            }
        }
        return null;
    }
}