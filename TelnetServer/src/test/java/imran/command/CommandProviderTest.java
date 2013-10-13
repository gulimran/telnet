package imran.command;

import org.junit.Test;

import static imran.command.CommandProvider.SupportedCommands.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 */
public class CommandProviderTest {

    private CommandProvider classToTest = new CommandProvider();

    @Test
    public void shouldReturnInstanceOfPwdCommand() {
        // given

        // when
        Command actual = classToTest.get(PWD, null);

        // then
        assertTrue(actual instanceof PwdCommand);
    }

    @Test
    public void shouldReturnInstanceOfCdCommand() {
        // given

        // when
        Command actual = classToTest.get(CD, null);

        // then
        assertTrue(actual instanceof CdCommand);
    }

    @Test
    public void shouldReturnInstanceOfLsCommand() {
        // given

        // when
        Command actual = classToTest.get(LS, null);

        // then
        assertTrue(actual instanceof LsCommand);
    }

    @Test
    public void shouldReturnInstanceOfMkdirCommand() {
        // given

        // when
        Command actual = classToTest.get(MKDIR, null);

        // then
        assertTrue(actual instanceof MkdirCommand);
    }
}
