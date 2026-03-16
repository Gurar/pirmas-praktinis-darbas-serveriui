package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.service;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.server.SocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final SocketServer socketServer;

    public CommandLineRunnerImpl(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    @Override
    public void run(String... args) throws Exception {
        socketServer.startServer();
    }
}
