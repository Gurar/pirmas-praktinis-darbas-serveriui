package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.server;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.handler.StudentFileClientHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
/**
 * Socket serverio komponentas. Laukia prisijungiančių klientų nurodytame prievade (port)
 * ir kiekvienam klientui sukuria naują giją (Thread), kurią perduoda apdoroti {@link StudentFileClientHandler}.
 */
@Component
public class SocketServer {
    @Value("${socket.server.port}")
    private int port;

    private final StudentFileClientHandler studentFileClientHandler;
    /**
     * Sukuria naują serverio egzempliorių.
     *
     * @param studentFileClientHandler Handleris, kuris apdoros kiekvieno prisijungusio kliento užklausą.
     */
    public SocketServer(StudentFileClientHandler studentFileClientHandler) {
        this.studentFileClientHandler = studentFileClientHandler;
    }
    /**
     * Paleidžia serverį nurodytame porte ir laukia klientų prisijungimų.
     * Šis metodas veikia begaliniame cikle (`while (true)`).
     */
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
