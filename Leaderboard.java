// Leaderboard.java
import java.io.*;
import java.util.*;

public class Leaderboard {
    private final String leaderboardFile;
    private final Map<String, Integer> leaderboardMap;

    /**
     * Constructor initializes the leaderboard with a file path.
     * @param leaderboardFile The file to store leaderboard data.
     */
    public Leaderboard(String leaderboardFile) {
        this.leaderboardFile = leaderboardFile;
        this.leaderboardMap = new HashMap<>();
        loadLeaderboard();
    }

    /**
     * Loads the leaderboard data from the file.
     */
    private void loadLeaderboard() {
        File file = new File(leaderboardFile);
        if (!file.exists()) {
            // Create the file if it doesn't exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating leaderboard file.");
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    int wins = Integer.parseInt(parts[1]);
                    leaderboardMap.put(playerName, wins);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading leaderboard file.");
        } catch (NumberFormatException e) {
            System.out.println("Leaderboard file is corrupted.");
        }
    }

    /**
     * Records a win for a player.
     * @param playerName The name of the player who won.
     */
    public void recordWin(String playerName) {
        int wins = leaderboardMap.getOrDefault(playerName, 0);
        leaderboardMap.put(playerName, wins + 1);
        saveLeaderboard();
    }

    /**
     * Saves the leaderboard data to the file.
     */
    private void saveLeaderboard() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(leaderboardFile))) {
            for (Map.Entry<String, Integer> entry : leaderboardMap.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to leaderboard file.");
        }
    }

    /**
     * Displays the current leaderboard.
     */
    public void displayLeaderboard() {
        System.out.println("\n===== Leaderboard =====");
        if (leaderboardMap.isEmpty()) {
            System.out.println("No records yet.");
            return;
        }

        // Sort players by number of wins in descending order
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(leaderboardMap.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(rank + ". " + entry.getKey() + " - " + entry.getValue() + " win(s)");
            rank++;
        }
        System.out.println("========================\n");
    }
}
