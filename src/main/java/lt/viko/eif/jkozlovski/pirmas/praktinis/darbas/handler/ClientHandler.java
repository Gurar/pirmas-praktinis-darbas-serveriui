package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.handler;

import java.net.Socket;
/**
 * Sąsaja, apibrėžianti prijungto kliento (Socket) apdorojimo logiką.
 */
public interface ClientHandler {
    /**
     * Apdoroja prijungto kliento užklausą.
     *
     * @param socket Kliento tinklo lizdas (Socket).
     */
    void handler(Socket socket);
}
