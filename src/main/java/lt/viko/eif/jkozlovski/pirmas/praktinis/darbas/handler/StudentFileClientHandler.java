package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.handler;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.file.FileProvider;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 * Kliento užklausų apdorojimo komponentas, kuris nuskaito "student.xml" failą
 * ir išsiunčia jo turinį prisijungusiam klientui per Socket srautą.
 */
@Component
public class StudentFileClientHandler implements ClientHandler {

    private final String fileName = "student.xml";

    private final FileProvider fileProvider;
    /**
     * Sukuria naują handlerio egzempliorių.
     *
     * @param fileProvider Komponentas, atsakingas už failų nuskaitymą.
     */
    public StudentFileClientHandler(FileProvider fileProvider) {
        this.fileProvider = fileProvider;
    }

    /**
     * Apdoroja kliento socket ryšį ir išsiunčia failą klientui.
     * Metodas naudoja `try-with-resources`, todėl srautai ir lizdas yra automatiškai uždaromi.
     *
     * @param socket Prisijungusio kliento lizdas.
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
