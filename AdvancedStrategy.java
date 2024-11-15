// AdvancedStrategy.java
public class AdvancedStrategy implements Strategy {
    /**
     * Decides the number of sticks to remove based on the current pile size.
     * Tries to leave the pile as 2^n - 1.
     * @param currentPileSize The current number of sticks in the pile.
     * @return The number of sticks to remove.
     */
    @Override
    public int decideMove(int currentPileSize) {
        int target = 1;
        while (target < currentPileSize) {
            target = (target + 1) * 2 - 1;
        }
        target = (target + 1) / 2 - 1;

        int move = currentPileSize - target;
        // Maximum move is floor(currentPileSize / 2)
        int maxMove = Math.max(1, currentPileSize / 2);
        move = Math.min(move, maxMove);
        if (move <= 0 || move > maxMove) {
            // If unable to reach target, take max allowed
            move = maxMove;
        }
        return move;
    }
}
