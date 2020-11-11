package ru.java.learn.connect;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Connect implements Closeable{
    private final Socket socket;
    private final BufferedWriter writer;
    private final BufferedReader reader;

    public Connect(String ip, int port){
        try {
            this.socket = new Socket(ip,port);
            this.reader = createReader();
            this.writer = createWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Connect(ServerSocket server){
        try {
            this.socket = server.accept();
            this.reader = createReader();
            this.writer = createWriter();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public void writeLine(String message){
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public String readLine(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private BufferedReader createReader() throws IOException {

        return new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    private BufferedWriter createWriter() throws IOException {

        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
