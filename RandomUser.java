import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RandomUser {
    public static void main(String[] args) {
        try {
            // URL FOR FETCHING RANDOM USER
            URL url = new URL("https://randomuser.me/api/?results=5000");
            // OPEN A CONNECTION TO URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // SET THE REQUEST METHOD TO GET
            connection.setRequestMethod("GET");

            // GET RESPONSE CODE
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                StringBuilder response = new StringBuilder();
                
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }

                // Print the fetched data
                System.out.println(response.toString());
            } else {
                // Print an error message
                System.out.println("Error: Unable to fetch data. Response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

