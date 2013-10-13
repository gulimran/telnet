package imran.command;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Base class for commands.  All classes extending from this should override the appropriate method.
 */
public class BaseCommand implements Command {

    /**
     * The current file path.
     */
    protected File currentPath;

    /**
     * Creates an instance of this class with the given current file path.
     *
     * @param currentPath - the current file path
     */
    public BaseCommand(File currentPath) {
        this.currentPath = currentPath;
    }

    /**
     * Executes the command for this class.
     *
     * @return a response string for command results
     * @throws IOException when fails to execute the command for this class
     */
    @Override
    public String execute() throws IOException {
        throw new IOException("This 'execute' method is not implemented at this time.");
    }

    /**
     * Executes the command for this class.
     *
     * @param args - one or more arguments to this command
     * @return a response file path for command result
     * @throws IOException when fails to execute the command for this class
     */
    @Override
    public File execute(String... args) throws IOException {
        throw new IOException("This 'execute' method is not implemented at this time.");
    }
}
