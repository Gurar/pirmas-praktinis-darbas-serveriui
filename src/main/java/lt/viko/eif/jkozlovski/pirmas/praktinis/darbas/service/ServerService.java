package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class ServerService {
    @Value("${socket.server.port}")
    private int port;

    @Autowired
    private FileService fileService;

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveris startavo porte: " + port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Klientas prisijunge!");

                    byte[] data = fileService.getFileContent("student.xml");

                    if (data.length == 0) {
                        System.out.println("Nepavyko gauti duomenu");
                        socket.close();
                        continue;
                    }

                    OutputStream output = socket.getOutputStream();
                    output.write(data);
                    output.flush();
                    output.close();
                    socket.close();

                    System.out.println("Failas sekmingai issiustas");
                } catch (IOException e) {
                    System.out.println("Klaida aptarnaujant klienta: " + e.getMessage());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
