import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 Executes Simple Magic game access Protocol commands
 from a socket.
 */
public class MagicService implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private MagicPlayerManager playerManager;

    public MagicService(Socket socket, MagicPlayerManager playerManager) {
        this.socket = socket;
        this.playerManager = playerManager;
    }

    public void run() {
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true); // Enable auto-flush
            doService();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doService() {
        try {
            while (true) {
                if (!in.hasNextLine()) {
                    return;
                }
                String commandLine = in.nextLine();
                if ("QUIT".equalsIgnoreCase(commandLine)) {
                    return;
                }
                executeCommand(commandLine);
            }
        } catch (Exception e) {
            out.println("Error processing command: " + e.getMessage());
        }
    } //performs commands till quit

    private void executeCommand(String commandLine) {
        String[] parts = commandLine.split(" ");
        if (parts.length < 2) {
            out.println("Invalid command format.");
            return;
        }

        String command = parts[0];
        int playerNumber;
        try {
            playerNumber = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            out.println("Invalid player number.");
            return;
        }
//Allows magic service to handle commands from the client
        switch (command.toUpperCase()) {
            case "DRAW":
                playerManager.drawCard(playerNumber);
                break;
            case "PLAY":
                playerManager.playCard(playerNumber);
                break;
            case "MULLIGAN":
                playerManager.mulligan(playerNumber);
                break;
            default:
                out.println("Unknown command.");
                return;
        }
        out.println("Player " + (playerNumber + 1) + " - Deck Size: " + playerManager.getDeckSize(playerNumber) + ", Hand Size: " + playerManager.getHandSize(playerNumber));
    }
}