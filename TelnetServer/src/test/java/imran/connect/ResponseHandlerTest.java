package imran.connect;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
@RunWith(MockitoJUnitRunner.class)
public class ResponseHandlerTest {

    private ResponseHandler classToTest;

    @Mock
    private Socket mockSocket;

    @Mock
    private PrintWriter mockPrintWriter;

    @Before
    public void setup() throws Exception {
        when(mockSocket.getOutputStream()).thenReturn(mock(OutputStream.class));
        classToTest = new ResponseHandler(mockSocket);
        classToTest.setWriter(mockPrintWriter);;
    }

    @Test
    public void shouldCallPrintWriterPrintlnOnSetLine() throws Exception {
        // given
        String expected = "test command output";

        // when
        classToTest.setLine(expected);

        // then
        verify(mockPrintWriter).println(eq(expected));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionOnSetLine() throws Exception {
        // given
        String expected = "test command output";
        doThrow(new RuntimeException("junit test - ignore")).when(mockPrintWriter).println(expected);

        // when
        classToTest.setLine(expected);

        // then
        fail("Exception is expected.");
    }
}
