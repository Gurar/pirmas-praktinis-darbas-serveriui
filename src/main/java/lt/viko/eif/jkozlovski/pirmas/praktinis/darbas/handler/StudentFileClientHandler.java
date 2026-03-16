package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.handler;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.file.FileProvider;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 * Klientu uz.
 */
@Component
public class StudentFileClientHandler implements ClientHandler {

    private final String fileName = "student.xml";

    private final FileProvider fileProvider;
    /**
     * @param fileProvider failu
     */
    public StudentFileClientHandler(FileProvider fileProvider) {
        this.fileProvider = fileProvider;
    }

    /**
     * Apdoroja kliento socket ir isiuncia faila klientui
     *
     * @param socket
     */
    @Override
    public void handler(Socket socket) {
        try (socket; OutputStream output = socket.getOutputStream()) {

            try (InputStream fileStream = fileProvider.getFileStream(fileName)) {

                fileStream.transferTo(output);

                output.flush();

                System.out.println("Failas sekmingai issiustas.");

            }
        } catch (FileNotFoundException e) {
            System.err.println("KLAIDA: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("KLAIDA: siunciant duomenis klientui" + e.getMessage());
        }
    }
}
