package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.file;

import java.io.IOException;
import java.io.InputStream;
/**
 * Sąsaja apibrėžianti failų nuskaitymo iš sistemos logiką.
 */
public interface FileProvider {
    /**
     * Nuskaito failą ir grąžina jo duomenų srautą.
     *
     * @param fileName Failo pavadinimas.
     * @return {@link InputStream} failo duomenų srautas.
     * @throws IOException Jei failo nepavyksta nuskaityti.
     */
    InputStream getFileStream(String fileName) throws IOException;
}
