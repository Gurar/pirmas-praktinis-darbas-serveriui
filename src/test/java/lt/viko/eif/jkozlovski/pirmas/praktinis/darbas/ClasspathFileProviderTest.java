package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.file.ClasspathFileProvider;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ClasspathFileProviderTest {

    private final ClasspathFileProvider classpathFileProvider = new ClasspathFileProvider();

    @Test
    void getFileStream_KaiFailoNera_MetaKlaida() {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            classpathFileProvider.getFileStream("neegzistuojantis_failas.xml");
        });

        System.out.println("KLAIDA: " + exception.getMessage());

        assertTrue(exception.getMessage().contains("nerastas"));
    }

    @Test
    void getFileStream_KaiFailasYra_GrazinaSrauta() throws Exception {
        InputStream result = classpathFileProvider.getFileStream("student.xml");

        assertNotNull(result, "Srautas neturi būti null");
        result.close();
    }
}
