package Server;

import Connect.Connect;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started!");
            while (true)
                try (Connect connect = new Connect(server))
                {
                    String request = connect.readLine();
                    System.out.println("Request: " + request);
                    String response = "Hello from server: " + request;
                    connect.writeLine(response);
                    System.out.println("Response: " + response);
                }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
