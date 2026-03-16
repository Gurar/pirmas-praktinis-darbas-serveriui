package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.file;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
@Component
public class ClasspathFileProvider implements FileProvider{
    /**
     * @param fileName
     */
    @Override
    public InputStream getFileStream(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);

        if (!resource.exists())
            throw  new FileNotFoundException("Failas " + fileName + " nerastas resources aplanke!");

        return resource.getInputStream();
    }
}
