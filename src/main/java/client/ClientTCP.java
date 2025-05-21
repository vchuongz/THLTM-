package client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientTCP {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 9999;
        String username = "user123";
        String filePath = "test.pdf"; // đường dẫn đến file bạn muốn gửi

        Socket socket = new Socket(host, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF(username);
        out.writeUTF(Paths.get(filePath).getFileName().toString());

        byte[] data = Files.readAllBytes(Paths.get(filePath));
        out.writeInt(data.length);
        out.write(data);

        socket.close();
        System.out.println("📤 File sent to server.");
    }
}