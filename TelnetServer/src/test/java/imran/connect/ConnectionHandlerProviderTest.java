package imran.connect;

import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
public class ConnectionHandlerProviderTest {

    private ConnectionHandlerProvider classToTest;

    @Test
    public void shouldReturnAnInstanceOfOpenConnectionHandler()throws Exception {
        // given
        classToTest = new ConnectionHandlerProvider();

        // when
        ConnectionHandler actual = classToTest.getConnectionHandler(mock(Socket.class));

        // then
        assertTrue(actual instanceof OpenConnectionHandler);
    }
}
