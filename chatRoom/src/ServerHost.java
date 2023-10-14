import java.io.*;
import java.net.*;

public class ServerHost {


    private ServerSocket serverSocket;

    public ServerHost() {
        try {
            InetAddress ipAddress = InetAddress.getByName("25.39.195.191"); // Replace with the desired IP address
            int port = 15555;

            serverSocket = new ServerSocket(port, 0, ipAddress);
            handleIncomingConnections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleIncomingConnections() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    try {
                        // Set up input and output streams for the client
                        OutputStream output = socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(output, true);
                        InputStream input = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                        // Handle client response
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println("Client says: " + line);

                            if (line.charAt(0) == '/') {
                                switch (line) {

                                    case "/help":
                                        writer.println("/quit");
                                        writer.println("/quit");
                                        writer.println("/quit");
                                        writer.println("/quit");
                                        break;
                                    case "/quit":
                                        socket.close();
                                        ;

                                    case "/game1":
                                        writer.println("WELCOME TO HIGHER OR LOWER");
                                        writer.println("Instructions: Guess the number randomly generated from 1-100");
                                        higherLower(writer, reader);
                                }


                            }

                            // Example: Send a response back to the client
                           // writer.println("Server received: " + line);

                            // You can add your logic here to process client requests
                            // and formulate responses accordingly.
                        }
                        if (line.isEmpty()) {
                            System.out.println("hi");
                        }
                        // Close resources when the client disconnects
                        reader.close();
                        writer.close();
                        socket.close();
                        System.out.println("Client disconnected: " + socket.getInetAddress().getHostAddress());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void higherLower(PrintWriter writer, BufferedReader reader) {
        writer.println("you have triggered the function correctly");


    }

    public static void main(String[] args) {
        new ServerHost();
    }
}
