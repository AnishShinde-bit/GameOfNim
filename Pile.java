// Pile.java
import java.util.Random;

public class Pile {
    private int totalSticks;
    public final int MIN_STICKS = 10;
    public final int MAX_STICKS = 30;

    /**
     * Constructor initializes the pile with a random number of sticks.
     */
    public Pile() {
        resetPile(generateRandomPileSize());
    }

    /**
     * Generates a random pile size between MIN_STICKS and MAX_STICKS.
     * @return The initial pile size.
     */
    public int generateRandomPileSize() {
        Random rand = new Random();
        return rand.nextInt(MAX_STICKS - MIN_STICKS + 1) + MIN_STICKS;
    }

    /**
     * Removes a specified number of sticks from the pile.
     * @param number The number of sticks to remove.
     */
    public void removeSticks(int number) {
        if (number > 0 && number <= totalSticks) {
            totalSticks -= number;
        }
    }

    /**
     * Gets the current total number of sticks in the pile.
     * @return The total number of sticks.
     */
    public int getTotalSticks() {
        return totalSticks;
    }

    /**
     * Resets the pile to a specified size.
     * @param initialSize The new size of the pile.
     */
    public void resetPile(int initialSize) {
        this.totalSticks = initialSize;
    }
}
