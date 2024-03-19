import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program tests the Magic server and "loosely" replicated a magic game.
 *
 * Colton Matthews
 * 3/18/2024
 * Sdev 301
 *  On a Side note while I did struggle with parts of this project I actually enjoyed the most of it
 * and had fun making it magic themed, might take your suggestion and keep working on it.
 */
public class MagicClient {
    public static void main(String[] args) throws IOException {
        final int MAGIC_PORT = 8888; //tests all code
        try (Socket socket = new Socket("localhost", MAGIC_PORT)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            Scanner in = new Scanner(inputStream);
            PrintWriter out = new PrintWriter(outputStream);

            String command = "DRAW 1";
            System.out.println("Sending: " + command);
            out.println(command);
            out.flush();
            String response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "MULLIGAN 1";
            System.out.println("Sending: " + command);
            out.println(command);
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "DRAW 2";
            System.out.println("Sending: " + command);
            out.println(command);
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "PLAY 1";
            System.out.println("Sending: " + command);
            out.println(command);
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "PLAY 2";
            System.out.println("Sending: " + command);
            out.println(command);
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);


            command = "QUIT";
            System.out.println("Sending: " + command);
            out.println(command);
            out.flush();
        }
    }
}