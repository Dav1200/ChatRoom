import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Replace with the server's IP address or hostname
        int serverPort = 25555; // Replace with the port number your server is listening on

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(serverAddress, serverPort);

            // Create input and output streams for communication with the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send a message to the server
            String message = "Hello, Server!";
            out.println(message);

            // Receive and print the server's response
            String serverResponse = in.readLine();
            System.out.println("Server says: " + serverResponse);

            // Close the socket when done
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
