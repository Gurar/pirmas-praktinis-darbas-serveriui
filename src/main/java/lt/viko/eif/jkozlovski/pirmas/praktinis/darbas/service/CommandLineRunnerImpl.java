package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.service;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.server.SocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/**
 * Komponentas, kuris automatiškai paleidžiamas po to, kai Spring Boot aplikacija yra pilnai užkraunama.
 * Ši klasė yra atsakinga už serverio inicijavimą.
 */
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final SocketServer socketServer;
    /**
     * Sukuria naują {@link CommandLineRunnerImpl} egzempliorių.
     *
     * @param socketServer komponentas, atsakingas už serverio paleidima.
     */
    public CommandLineRunnerImpl(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    @Override
    public void run(String... args) throws Exception {
        socketServer.startServer();
    }
}
