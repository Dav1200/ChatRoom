import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        String serverAddress = "25.39.195.191"; // Replace with the server's IP address or hostname
        int serverPort = 15555; // Replace with the port number your server is listening on

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(serverAddress, serverPort);

            // Create input and output streams for communication with the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                // Read a message from the console
                System.out.print("Enter a message to send to the server: ");
                String message = consoleInput.readLine();

                // Send the message to the server
                out.println(message);

                // Receive and print the server's response
                String serverResponse = in.readLine();
                System.out.println("Server says: " + serverResponse);

                // Optionally, you can add a condition to exit the loop
                // if a certain message is received from the server.
                // For example:
                // if ("exit".equalsIgnoreCase(serverResponse)) {
                //     break;
                // }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
