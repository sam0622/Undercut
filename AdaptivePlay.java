import java.util.Arrays;

public class AdaptivePlay extends Player {

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
        if (this.roundsPlayed > 1) {
            this.trimmedOpHistory = Arrays.copyOfRange(this.opHistory, 1, this.roundsPlayed);
            this.identifiedPattern = patternSearch(this.trimmedOpHistory);
        }

        this.roundsPlayed++;
        return calculateIdealPlay();
    }

    /**
     * If a pattern is identified, it plays the next number in line, minus 1. Returns random from 4 to 6 otherwise
     *
     * @return the number to be played
     */
    private int calculateIdealPlay() {
        if (this.identifiedPattern == null) {
            return (int) (Math.random() * 6) + 2;
        }
        int p = (this.roundsPlayed + 2) % this.identifiedPattern.length; // Goes to array index based on rounds
        return this.identifiedPattern[p] - 1;
    }

    /**
     * Uses a brute-force pattern matching algorithm to find patterns
     * The basic principle is that if you're searching for 3 long patterns in a 9 long array,
     * You can check if the 3 spaces after the end of the pattern are the same as the pattern
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