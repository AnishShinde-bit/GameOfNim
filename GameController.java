// GameController.java
public class GameController {
    private final NimGame game;
    private final GameUI ui;
    private final Leaderboard leaderboard;

    /**
     * Constructor to create a new GameController.
     * @param game The NimGame instance.
     * @param ui The GameUI instance.
     */
    public GameController(NimGame game, GameUI ui) {
        this.game = game;
        this.ui = ui;
        this.leaderboard = new Leaderboard("leaderboard.txt");
    }

    /**
     * Runs the main game loop.
     */
    public void runGameLoop() {
        ui.displayWelcome();
        boolean playAgain = true;

        while (playAgain) {
            game.resetGame();
            playSingleGame();
            playAgain = ui.askPlayAgain();
        }

        ui.displayMessage("Thank you for playing Game of Nim!");
    }

    /**
     * Plays a single game until it's over.
     */
    private void playSingleGame() {
        while (!game.isGameOver()) {
            Player currentPlayer = game.getCurrentPlayer();
            ui.displayMessage("\nCurrent pile size: " + game.getPile().getTotalSticks());
            int move = ui.promptMove(currentPlayer, game.getPile().getTotalSticks());

            // Remove sticks based on the move
            game.getPile().removeSticks(move);

            if (game.isGameOver()) {
                // Last player to take a stick loses, so the other player wins
                Player winner = game.getWinner();
                ui.displayWinner(winner);
                leaderboard.recordWin(winner.getName());
                leaderboard.displayLeaderboard();
            } else {
                game.switchPlayer();
            }
        }
    }
}
