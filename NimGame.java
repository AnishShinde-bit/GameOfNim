// NimGame.java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NimGame {
    private final List<Player> players;
    private final Pile pile;
    private final GameUI ui;
    private final GameController controller;
    private Player currentPlayer;

    /**
     * Constructor initializes the game components.
     */
    public NimGame() {
        ui = new GameUI();
        controller = new GameController(this, ui);
        players = new ArrayList<>();
        pile = new Pile(); // Pile's constructor calls resetPile(int)
        initializePlayers();
        chooseFirstPlayer();
    }

    /**
     * Initializes the players in the game.
     * Adds one human player and one computer player.
     */
    private void initializePlayers() {
        String playerName = ui.getPlayerName();
        players.add(new Player(playerName, false));
        players.add(new Player("Computer", true));
    }

    /**
     * Randomly selects the first player to start the game.
     */
    private void chooseFirstPlayer() {
        Random rand = new Random();
        int first = rand.nextInt(players.size());
        currentPlayer = players.get(first);
        ui.displayMessage(currentPlayer.getName() + " will start first.");
    }

    /**
     * Switches the turn to the next player.
     */
    public void switchPlayer() {
        int currentIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentIndex + 1) % players.size());
    }

    /**
     * Checks if the game has ended.
     * @return true if the pile is empty, false otherwise.
     */
    public boolean isGameOver() {
        return pile.getTotalSticks() <= 0;
    }

    /**
     * Gets the winner of the game.
     * @return The player who did not take the last stick.
     */
    public Player getWinner() {
        // The player who did not take the last stick wins
        int lastIndex = players.indexOf(currentPlayer);
        int winnerIndex = (lastIndex + 1) % players.size();
        return players.get(winnerIndex);
    }

    /**
     * Gets the current player.
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the pile of sticks.
     * @return The pile.
     */
    public Pile getPile() {
        return pile;
    }

    /**
     * Starts and manages the game loop.
     */
    public void play() {
        controller.runGameLoop();
    }

    /**
     * Resets the game components for a new game.
     */
    public void resetGame() {
        int newPileSize = ui.chooseInitialPileSize();
        pile.resetPile(newPileSize);
        chooseFirstPlayer();
    }

    // Additional methods and getters can be added as needed.
}
