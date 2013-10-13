package imran.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
@RunWith(MockitoJUnitRunner.class)
public class PwdCommandTest {

    @InjectMocks
    private PwdCommand classToTest;

    @Mock
    private File mockPath;

    @Test
    public void shouldGetCanonicalPath() throws Exception {
        // given

        // when
        classToTest.execute();

        // then
        verify(mockPath).getCanonicalPath();
    }

    @Test
    public void shouldReturnExpectedPath() throws Exception {
        // given
        String expected = "/tmp";
        when(mockPath.getCanonicalPath()).thenReturn(expected);

        // when
        String actual = classToTest.execute();

        // then
        assertEquals(expected, actual);
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOException() throws Exception {
        // given
        when(mockPath.getCanonicalPath()).thenThrow(new IOException("junit test - ignore"));

        // when
        String actual = classToTest.execute();

        // then
        fail("IOException is expected.");
    }
}
