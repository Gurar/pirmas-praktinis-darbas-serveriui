package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {
    public byte[] getFileContent(String fileName) throws IOException {

        ClassPathResource resource = new ClassPathResource(fileName);

        if (!resource.exists())
            throw new IOException("Failas " + fileName + " nerastas resources aplanke!");

        try (InputStream inputStream = resource.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }
}
