package imran.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
public class MkdirCommandTest {

    private static final String newDirName = "testDir";

    private File newDir;
    private MkdirCommand classToTest;

    @Before
    public void setup() {
        newDir = new File(newDirName);
    }

    @Test
    public void shouldCreateNewDirectory() throws Exception {
        // given
        File expected = new File(System.getProperty("user.dir"));
        classToTest = new MkdirCommand(expected);

        // when
        classToTest.execute(newDirName);

        // then
        assertTrue(newDir.exists());
    }

    @After
    public void teardown() {
        if (newDir.exists()) {
            newDir.delete();
        }
    }


}
