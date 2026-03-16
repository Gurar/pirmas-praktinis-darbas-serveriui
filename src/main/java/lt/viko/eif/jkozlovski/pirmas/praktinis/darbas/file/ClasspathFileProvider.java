package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.file;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * {@link FileProvider} implementacija, skirta failų nuskaitymui iš `classpath` (resources) aplanko.
 */
@Component
public class ClasspathFileProvider implements FileProvider{
    /**
     * Nuskaito failą iš `resources` aplanko.
     *
     * @param fileName Failo pavadinimas.
     * @return Failo įvesties srautas (InputStream).
     * @throws IOException Jei failas nerastas arba jo nepavyksta atidaryti.
     */
    @Override
    public InputStream getFileStream(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);

        if (!resource.exists())
            throw  new FileNotFoundException("Failas " + fileName + " nerastas resources aplanke!");

        return resource.getInputStream();
    }
}
