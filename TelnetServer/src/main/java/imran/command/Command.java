package imran.command;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * The interface for an OS command.
 */
public interface Command {

    /**
     * Executes the command for this class.
     *
     * @return a response string for command results
     * @throws IOException when fails to execute the command for this class
     */
    String execute() throws IOException;

    /**
     * Executes the command for this class.
     *
     * @param args - one or more arguments to this command
     * @return a response file path for command result
     * @throws IOException when fails to execute the command for this class
     */
    File execute(String... args) throws IOException;
}
