package Client;

import Connect.Connect;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try (Connect connect = new Connect("127.0.0.1", 8000))
        {
            System.out.println("Connected to server");
            String request = "Astana";

            connect.writeLine(request);
            String response = connect.readLine();
            System.out.println("Response: " + response);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
