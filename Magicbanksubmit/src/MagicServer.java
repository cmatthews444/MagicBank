import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server that executes commands for managing Magic games.
 */
public class MagicServer {
    public static void main(String[] args) throws IOException {
        final int NUM_PLAYERS = 4; // Adjust as needed
        MagicPlayerManager playerManager = new MagicPlayerManager(NUM_PLAYERS);
        final int MAGIC_PORT = 8888; // Adjust as needed
        ServerSocket server = new ServerSocket(MAGIC_PORT);
        System.out.println("Waiting for clients to connect...");

        while (true) {
            Socket socket = server.accept();
            System.out.println("Client connected.");
            MagicService service = new MagicService(socket, playerManager);
            Thread thread = new Thread(service);
            thread.start();
        }
    }
}
