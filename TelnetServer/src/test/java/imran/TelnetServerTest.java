package imran;

import imran.connect.ConnectionHandler;
import imran.connect.ConnectionHandlerProvider;
import imran.socket.SocketFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static imran.TelnetServer.PORT;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
@RunWith(MockitoJUnitRunner.class)
public class TelnetServerTest {

    @InjectMocks
    private TelnetServer classToTest;

    @Mock
    private SocketFactory mockSocketFactory;

    @Mock
    private ConnectionHandlerProvider mockHandlerProvider;

    @Mock
    private ServerSocket mockServerSocket;

    @Test
    public void shouldGetServerSocketFromFactory() throws Exception {
        // given

        // when
        classToTest.forServerSocket();

        // then
        verify(mockSocketFactory).getServerSocket(eq(PORT));
    }

    @Test(expected = IOException.class)
    public void shouldFailToGetServerSocketFromFactory() throws Exception {
        // given
        when(mockSocketFactory.getServerSocket(eq(PORT))).thenThrow(new IOException("junit test - ignore"));

        // when
        classToTest.forServerSocket();

        // then
        // should throw IOException
    }

    @Test
    public void serverSocketShouldAcceptConnection() throws Exception {
        // given
        when(mockSocketFactory.getServerSocket(eq(PORT))).thenReturn(mockServerSocket);
        classToTest.forServerSocket();

        // when
        classToTest.accept();

        // then
        verify(mockServerSocket).accept();
    }


    @Test(expected = IOException.class)
    public void shouldFailToAcceptConnection() throws Exception {
        // given
        when(mockSocketFactory.getServerSocket(eq(PORT))).thenReturn(mockServerSocket);
        when(mockServerSocket.accept()).thenThrow(new IOException("junit test - ignore"));
        classToTest.forServerSocket();

        // when
        classToTest.accept();

        // then
        // should throw IOException
    }

    @Test
    public void shouldStartConnection() throws Exception{
        // given
        Socket mockSocket = mock(Socket.class);
        ConnectionHandler mockHandler = mock(ConnectionHandler.class);
        when(mockSocketFactory.getServerSocket(eq(PORT))).thenReturn(mockServerSocket);
        when(mockHandlerProvider.getConnectionHandler(mockSocket)).thenReturn(mockHandler);
        when(mockServerSocket.accept()).thenReturn(mockSocket);
        when(mockSocket.getInputStream()).thenReturn(mock(InputStream.class));
        classToTest.forServerSocket().accept();

        // when
        classToTest.connection();

        // then
        verify(mockHandler).startConnection();
    }
}
