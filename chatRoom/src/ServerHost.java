import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

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
                        writer.println("Please Provide your NickName ");
                        String name = reader.readLine();
                        // Handle client response
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(name + ": " + line);

                            if (line.isEmpty()) {
                                // Handle an empty line, e.g., ignore or respond
                                continue;
                            }

                            if (line.charAt(0) == '/') {
                                switch (line) {
                                    case "/help":
                                        writer.println("Welcome To My Server, The current list of commands are listed belelow\n/quit, /game1");

                                        break;
                                    case "/quit":
                                        socket.close();
                                        break; // Added a break to exit the switch statement
                                    case "/game1":
                                        higherLower(writer, reader);
                                        break; // Added a break to exit the switch statement
                                }
                            }

                            // Example: Send a response back to the client
                            // writer.println("Server received: " + line);

                            // You can add your logic here to process client requests
                            // and formulate responses accordingly.
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


    public static void higherLower(PrintWriter writer, BufferedReader reader) throws IOException {
        Random rand = new Random();

        int min = 1;
        int max = 10;
        int randomNumber = rand.nextInt(max - min + 1) + min;
        int numberOfTries = 0;

        writer.println("Welcome to the Number Guessing Game!");
        writer.println("I've selected a random number between 1 and 100. Try to guess it.");

        while (true) {
            writer.println("Enter your guess: ");
            writer.flush(); // Ensure the prompt is sent to the client immediately.

            String userInput = reader.readLine();
            int userGuess;

            try {
                userGuess = Integer.parseInt(userInput);
                numberOfTries++;

                if (userGuess < min || userGuess > max) {
                    writer.println("Please enter a number between 1 and 100.");
                } else if (userGuess < randomNumber) {
                    writer.println("Try a higher number.");
                } else if (userGuess > randomNumber) {
                    writer.println("Try a lower number.");
                } else {
                    writer.println("Congratulations! You've guessed the correct number: " + randomNumber);
                    writer.println("It took you " + numberOfTries + " tries.");
                    break;
                }
            } catch (NumberFormatException e) {
                writer.println("Invalid input. Please enter a valid number.");
            }
        }
    }



    public static void main(String[] args) {
        new ServerHost();
    }
}
