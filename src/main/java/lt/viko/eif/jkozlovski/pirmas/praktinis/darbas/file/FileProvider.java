package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.file;

import java.io.IOException;
import java.io.InputStream;

public interface FileProvider {
    InputStream getFileStream(String fileName) throws IOException;
}
