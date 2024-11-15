// InputValidator.java
import java.util.Scanner;

public class InputValidator {
    private final Scanner scanner;

    /**
     * Constructor to create a new InputValidator.
     * @param scanner The Scanner instance for input.
     */
    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Validates that the input is an integer within the allowed range.
     * @param prompt The message to prompt the user.
     * @param min The minimum valid value.
     * @param max The maximum valid value.
     * @return A valid integer input from the user.
     */
    public int validateInput(String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                input = Integer.parseInt(line);
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Input must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        return input;
    }
}
