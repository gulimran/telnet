package imran.command;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Provides the current working directory.
 */
public class PwdCommand extends BaseCommand {

    /**
     * Creates an instance of the print working directory class for the given current directory path.
     *
     * @param currentPath - the path file for the current working directory
     */
    public PwdCommand(File currentPath) {
        super(currentPath);
    }

    /**
     * Provides the path to the current working directory.
     *
     * @return the path to the current working directory
     * @throws IOException when fails to provide the path to the current working directory
     */
    @Override
    public String execute() throws IOException {
        return currentPath.getCanonicalPath();
    }
}
