package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private ServerService serverService;

    @Override
    public void run(String... args) throws Exception {
        serverService.startServer();
    }
}
