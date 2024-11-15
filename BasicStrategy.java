// BasicStrategy.java
public class BasicStrategy implements Strategy {
    /**
     * Decides to remove one stick.
     * @param currentPileSize The current number of sticks in the pile.
     * @return The number of sticks to remove (always 1).
     */
    @Override
    public int decideMove(int currentPileSize) {
        return 1;
    }
}
