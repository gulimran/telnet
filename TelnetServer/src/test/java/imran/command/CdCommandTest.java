package imran.command;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
public class CdCommandTest {

    private CdCommand classToTest;

    @Test
    public void shouldChangeToNewPath() throws Exception {
        // given
        String starting = System.getProperty("user.dir");
        String expected = System.getProperty("user.home");
        classToTest = new CdCommand(new File(starting));

        // when
        File actual = classToTest.execute(expected);

        // then
        assertEquals(expected, actual.getCanonicalPath());

    }
}
