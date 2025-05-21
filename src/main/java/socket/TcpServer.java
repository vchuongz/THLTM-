package socket;

import model.Job;
import queue.JobQueue;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("🚀 TCP Server started on port 9999...");

        while (true) {
            Socket client = serverSocket.accept();
            new Thread(() -> handleClient(client)).start();
        }
    }

    private static void handleClient(Socket socket) {
        try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
            String username = in.readUTF();
            String filename = in.readUTF();
            int length = in.readInt();
            byte[] data = new byte[length];
            in.readFully(data);

            Path uploadDir = Paths.get("uploads");
            Files.createDirectories(uploadDir);
            Path filePath = uploadDir.resolve(filename);
            Files.write(filePath, data);

            Job job = new Job(filename, filePath.toString());
            JobQueue.add(job);
            System.out.println("📥 File received from " + username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}