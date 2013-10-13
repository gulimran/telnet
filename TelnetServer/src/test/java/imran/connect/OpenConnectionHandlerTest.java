package imran.connect;

import imran.command.BaseCommand;
import imran.command.CommandProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static imran.command.CommandProvider.SupportedCommands.*;
import static imran.connect.OpenConnectionHandler.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
@RunWith(MockitoJUnitRunner.class)
public class OpenConnectionHandlerTest {

    @InjectMocks
    private OpenConnectionHandler classToTest;

    @Mock
    private Socket socket;

    @Mock
    private RequestHandler mockRequestHandler;

    @Mock
    private ResponseHandler mockResponseHandler;

    @Mock
    private InetAddress mockInetAddress;

    @Mock
    private CommandProvider mockCommandProvider;

    @Before
    public void setup() throws Exception {
        when(socket.getInetAddress()).thenReturn(mockInetAddress);
        when(mockCommandProvider.get(any(CommandProvider.SupportedCommands.class), any(File.class))).thenReturn(mock(BaseCommand.class));
        classToTest.setRequestHandler(mockRequestHandler);
        classToTest.setResponseHandler(mockResponseHandler);
        classToTest.setCommandProvider(mockCommandProvider);
    }

    @Test
    public void shouldStartThread() throws Exception {
        // given
        when(mockRequestHandler.getLine()).thenReturn("ls");

        // when
        Thread expected = classToTest.startConnection();

        // then
        assertTrue(expected.isAlive());
    }

    @Test
    public void shouldEndThreadOnQuit() throws Exception {
        // given
        when(mockRequestHandler.getLine()).thenReturn("quit");

        // when
        Thread expected = classToTest.startConnection();
        waitForThreadCompletion(expected);

        // then
        assertFalse(expected.isAlive());
    }

    @Test
    public void shouldEndThreadOnExit() throws Exception {
        // given
        when(mockRequestHandler.getLine()).thenReturn("exit");

        // when
        Thread expected = classToTest.startConnection();
        waitForThreadCompletion(expected);

        // then
        assertFalse(expected.isAlive());
    }

    @Test
    public void shouldNotCallResponseHandlerOnRequestHandlerInitializationError() throws Exception {
        // given
        doThrow(new IOException("junit test - ignore")).when(mockRequestHandler).initialize();

        // when
        classToTest.run();

        // then
        verifyZeroInteractions(mockResponseHandler);

    }

    @Test
    public void shouldNotCallResponseHandlerSetLineOnResponseHandlerInitializationError() throws Exception {
        // given
        doThrow(new IOException("junit test - ignore")).when(mockResponseHandler).initialize();

        // when
        classToTest.run();

        // then
        verify(mockResponseHandler, times(0)).setLine(anyString());

    }

    @Test
    public void shouldRespondWithWelcomeMessage() throws Exception {
        // given
        when(mockRequestHandler.getLine()).thenReturn("quit");

        // when
        classToTest.run();

        // then
        verify(mockResponseHandler).setLine(eq(WELCOME_MSG));
    }

    @Test
    public void shouldRespondWithClientHostname() throws Exception {
        // given
        final String hostname = "Unit Test Host";
        when(mockInetAddress.getHostName()).thenReturn(hostname);
        when(mockRequestHandler.getLine()).thenReturn("quit");

        // when
        classToTest.run();

        // then
        verify(mockResponseHandler).setLine(eq(CONNECTED_FROM_MSG + hostname));
    }

    @Test
    public void shouldOnlyCallResponseHandlerWithWelcomeMessageOnRequestHandlerGetLineError() throws Exception {
        // given
        final String hostname = "Unit Test Host";
        when(mockInetAddress.getHostName()).thenReturn(hostname);
        when(mockRequestHandler.getLine()).thenThrow(new IOException("junit test - ignore"));

        // when
        classToTest.run();

        // then
        InOrder order = inOrder(mockResponseHandler);
        order.verify(mockResponseHandler).setLine(eq(WELCOME_MSG));
        order.verify(mockResponseHandler).setLine(eq(CONNECTED_FROM_MSG + hostname));
        verify(mockResponseHandler, times(2)).setLine(anyString());
    }

    @Test
    public void shouldRespondToPwdCommand() throws Exception {
        // given
        when(mockRequestHandler.getLine()).thenReturn("pwd").thenReturn("quit");

        // when
        classToTest.run();

        // then
        verify(mockCommandProvider).get(eq(PWD), any(File.class));
    }

    @Test
    public void shouldRespondToLsCommand() throws Exception {
        // given
        when(mockRequestHandler.getLine()).thenReturn("ls").thenReturn("quit");

        // when
        classToTest.run();

        // then
        verify(mockCommandProvider).get(eq(LS), any(File.class));
    }

    @Test
    public void shouldRespondToCdCommand() throws Exception {
        // given
        when(mockRequestHandler.getLine()).thenReturn("cd /tmp").thenReturn("quit");

        // when
        classToTest.run();

        // then
        verify(mockCommandProvider).get(eq(CD), any(File.class));
    }

    @Test
    public void shouldRespondToMkdirCommand() throws Exception {
        // given
        when(mockRequestHandler.getLine()).thenReturn("mkdir testDir").thenReturn("quit");

        // when
        classToTest.run();

        // then
        verify(mockCommandProvider).get(eq(MKDIR), any(File.class));
    }

    @Test
    public void shouldRespondToUnknownCommand() throws Exception {
        // given
        String expected = "unknown command";
        when(mockRequestHandler.getLine()).thenReturn(expected).thenReturn("quit");

        // when
        classToTest.run();

        // then
        verify(mockResponseHandler).setLine(eq(ERROR_UNKNOWN_COMMAND + expected));
    }

    private void waitForThreadCompletion(Thread thread) throws Exception {
        long startTime = System.currentTimeMillis();
        long timer = 0;
        long max = 10000;
        long interval = 100;

        while (thread.isAlive() && timer < max)  {
            timer = System.currentTimeMillis() - startTime;
            Thread.sleep(interval);
        }
    }
}
