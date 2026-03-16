package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.server;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.handler.StudentFileClientHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;

@Component
public class SocketServer {
    @Value("${socket.server.port}")
    private int port;

    private final StudentFileClientHandler studentFileClientHandler;

    public SocketServer(StudentFileClientHandler studentFileClientHandler) {
        this.studentFileClientHandler = studentFileClientHandler;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveris startavo porte " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Klientas prisijungie");

                new Thread(() -> studentFileClientHandler.handler(socket)).start();
            }
        } catch (Exception e) {
            System.out.println("Serverio klaida: " + e.getMessage());
        }
    }

}
