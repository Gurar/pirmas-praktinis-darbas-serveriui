package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.handler.StudentFileClientHandler;
import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.server.SocketServer;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;

public class SocketServerTest {

    @Test
    void shouldCallHandlerWhenClientConnects() throws Exception {

        StudentFileClientHandler handler = mock(StudentFileClientHandler.class);

        SocketServer server = new SocketServer(handler);

        int testPort = 8089;
        ReflectionTestUtils.setField(server, "port", testPort);

        ExecutorService gijuValdytojas = Executors.newSingleThreadExecutor();
        gijuValdytojas.submit(() -> server.startServer());

        Thread.sleep(200);

        try (Socket clientSocket = new Socket("localhost", testPort)) {

        }

        verify(handler, timeout(1000).times(1)).handler(any(Socket.class));

        gijuValdytojas.shutdownNow();
    }
}
