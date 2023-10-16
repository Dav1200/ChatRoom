import java.io.IOException;

public class LaunchExternalJar {
        public static void main(String[] args) {
            String jarPath = "C:\\Users\\harvi\\Documents\\GitHub\\ChatRoom\\chatRoom\\src\\weatherApp - Copy.jar"; // Replace with the actual path to the JAR file

            try {
                ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarPath);
                Process process = processBuilder.start();

                // Optional: You can wait for the external JAR process to complete
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    System.out.println("External JAR executed successfully.");
                } else {
                    System.err.println("External JAR execution failed with exit code: " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

