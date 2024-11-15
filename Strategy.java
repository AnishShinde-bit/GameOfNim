// Strategy.java
public interface Strategy {
    /**
     * Decides the number of sticks to remove based on the current pile size.
     * @param currentPileSize The current number of sticks in the pile.
     * @return The number of sticks to remove.
     */
    int decideMove(int currentPileSize);
}
