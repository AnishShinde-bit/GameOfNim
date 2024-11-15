// Player.java
public class Player {
    private final String name;
    private final boolean isComputer;
    private Strategy strategy;

    /**
     * Constructor to create a new player.
     * @param name The name of the player.
     * @param isComputer True if the player is a computer, false if human.
     */
    public Player(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
        if (isComputer) {
            this.strategy = chooseStrategy();
        }
    }

    /**
     * Chooses the strategy for computer players.
     * @return An instance of a Strategy implementation.
     */
    private Strategy chooseStrategy() {
        // You can choose between BasicStrategy and AdvancedStrategy
        // For demonstration, we'll use AdvancedStrategy
        return new AdvancedStrategy();
    }

    /**
     * Gets the name of the player.
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the player is a computer.
     * @return True if computer, false otherwise.
     */
    public boolean isComputer() {
        return isComputer;
    }

    /**
     * Sets the strategy for a computer player.
     * @param strategy The strategy to set.
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Determines the number of sticks to remove.
     * @param currentPileSize The current number of sticks in the pile.
     * @return The number of sticks to remove.
     */
    public int getMove(int currentPileSize) {
        if (isComputer) {
            return strategy.decideMove(currentPileSize);
        } else {
            // Human player move is handled via GameUI
            return 0;
        }
    }
}
