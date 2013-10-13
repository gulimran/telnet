package imran.connect;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
@RunWith(MockitoJUnitRunner.class)
public class RequestHandlerTest {

    private RequestHandler classToTest;

    @Mock
    private Socket mockSocket;


    @Mock
    private BufferedReader mockBufferedReader;

    @Before
    public void setup() throws Exception {
        when(mockSocket.getInputStream()).thenReturn(mock(InputStream.class));
        classToTest = new RequestHandler(mockSocket);
        classToTest.setReader(mockBufferedReader);
    }

    @Test
    public void shouldReturnExpectedLine() throws Exception {
        // given
        String expected = "test command";
        when(mockBufferedReader.readLine()).thenReturn(expected);

        // when
        String actual = classToTest.getLine();

        // then
        assertEquals(expected, actual);
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOExceptionOnGetLine() throws Exception {
        // given
        when(mockBufferedReader.readLine()).thenThrow(new IOException("junit test - ignore"));

        // when
        String actual = classToTest.getLine();

        // then
        fail("IOException is expected.");
    }
}
