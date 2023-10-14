import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        final String serverAddress = "25.39.195.191"; // Change this to your server's IP address or hostname
        final int serverPort = 15555; // Change this to your server's port

        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)) ) {

            System.out.println("Connected to the server.");

            Thread serverListener = new Thread(() -> {
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Server: " + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverListener.start();

            while (true) {
                String message = userInput.readLine();
                if (message.equals("exit")) {
                    // Exit the loop if the user types "exit"
                    break;
                }
                System.out.println("Client: " + message);
                writer.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}