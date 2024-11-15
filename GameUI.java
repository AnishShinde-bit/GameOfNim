// GameUI.java
import java.util.Scanner;

public class GameUI {
    private final Scanner scanner;
    private final InputValidator validator;

    /**
     * Constructor initializes the scanner and input validator.
     */
    public GameUI() {
        scanner = new Scanner(System.in);
        validator = new InputValidator(scanner);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void displayWelcome() {
        System.out.println("=================================");
        System.out.println("      Welcome to Game of Nim     ");
        System.out.println("=================================");
    }

    /**
     * Prompts the human player to enter their name.
     * @return The entered player name.
     */
    public String getPlayerName() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Please enter your name: ");
            name = scanner.nextLine().trim();
        }
        return name;
    }

    /**
     * Displays a generic message to the user.
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompts the player to make a move.
     * @param player The current player.
     * @param currentPileSize The current number of sticks in the pile.
     * @return The number of sticks to remove.
     */
    public int promptMove(Player player, int currentPileSize) {
        if (player.isComputer()) {
            // Computer move is handled automatically
            int move = player.getMove(currentPileSize);
            displayMessage(player.getName() + " removes " + move + " stick(s).");
            return move;
        } else {
            String prompt = player.getName() + ", enter the number of sticks to remove (1-" + currentPileSize + "): ";
            // Maximum move is floor(currentPileSize / 2)
            int maxMove = Math.max(1, currentPileSize / 2);
            return validator.validateInput(prompt, 1, maxMove);
        }
    }

    /**
     * Displays the winner of the game.
     * @param player The winning player.
     */
    public void displayWinner(Player player) {
        System.out.println("=================================");
        System.out.println(player.getName() + " wins the game!");
        System.out.println("=================================");
    }

    /**
     * Asks the user if they want to play again.
     * @return True if the user wants to play again, false otherwise.
     */
    public boolean askPlayAgain() {
        while (true) {
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    /**
     * Allows the user to choose the initial number of sticks.
     * @return The chosen pile size.
     */
    public int chooseInitialPileSize() {
        String prompt = "Enter the initial number of sticks (minimum 10): ";
        int initialSize = validator.validateInput(prompt, 10, 1000); // Upper limit to prevent too large piles
        displayHint(initialSize);
        return initialSize;
    }

    /**
     * Displays hints to the user based on the current pile size.
     * @param pileSize The current number of sticks in the pile.
     */
    public void displayHint(int pileSize) {
        if (pileSize >= 2) {
            System.out.println("Hint: Try to leave your opponent with a pile size of 2^n - 1 sticks.");
            System.out.println("For example, leave them with 3, 7, 15, etc., sticks to increase your chances of winning.");
        }
    }
}
