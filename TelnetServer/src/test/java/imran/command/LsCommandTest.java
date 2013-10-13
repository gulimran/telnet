package imran.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
@RunWith(MockitoJUnitRunner.class)
public class LsCommandTest {

    @InjectMocks
    private LsCommand classToTest;

    @Mock
    private File path;

    @Test
    public void shouldReturnListOfFilesInCurrentDirectory() throws Exception {
        // given
        String[] expected  = {"file1", "file2", "dir1", "dir2"};
        when(path.list()).thenReturn(expected);

        // when
        String actual = classToTest.execute();

        // then
        assertEquals(Arrays.asList(expected).toString(), actual);
    }

    @Test
    public void shouldReturnEmptyListOfFilesInCurrentDirectory() throws Exception {
        // given
        String[] expected = new String[0];
        when(path.list()).thenReturn(expected);

        // when
        String actual = classToTest.execute();

        // then
        assertEquals(Arrays.asList(expected).toString(), actual);
    }
}
