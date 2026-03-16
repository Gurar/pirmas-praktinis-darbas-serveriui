package lt.viko.eif.jkozlovski.pirmas.praktinis.darbas;

import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.file.FileProvider;
import lt.viko.eif.jkozlovski.pirmas.praktinis.darbas.handler.StudentFileClientHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.Socket;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentFileClientHandlerTest {

    @Test
    void shouldSendFileToClient() throws Exception {

        // mock FileProvider
        FileProvider fileProvider = mock(FileProvider.class);

        byte[] fileData = "test xml".getBytes();
        InputStream inputStream = new ByteArrayInputStream(fileData);

        when(fileProvider.getFileStream("student.xml"))
                .thenReturn(inputStream);

        // mock socket
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(socket.getOutputStream()).thenReturn(outputStream);

        // testuojamas objektas
        StudentFileClientHandler handler =
                new StudentFileClientHandler(fileProvider);

        handler.handler(socket);

        // patikriname ar duomenys išsiųsti
        assertTrue(outputStream.size() > 0);

        // patikriname ar kviečiamas FileProvider
        verify(fileProvider).getFileStream("student.xml");
    }

    @Test
    void shouldHandleFileNotFound() throws Exception {

        FileProvider fileProvider = mock(FileProvider.class);

        when(fileProvider.getFileStream("student.xml"))
                .thenThrow(new FileNotFoundException("Failas nerastas"));

        Socket socket = mock(Socket.class);
        when(socket.getOutputStream())
                .thenReturn(new ByteArrayOutputStream());

        StudentFileClientHandler handler =
                new StudentFileClientHandler(fileProvider);

        handler.handler(socket);

        verify(fileProvider).getFileStream("student.xml");
    }
}
