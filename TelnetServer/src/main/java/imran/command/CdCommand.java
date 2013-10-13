package imran.command;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Change directory command.
 */
public class CdCommand extends BaseCommand {

    /**
     * Creates an instance of change directory command for given current file path.
     *
     * @param currentPath - the current file path
     */
    public CdCommand(File currentPath) {
        super(currentPath);
    }

    /**
     * Changes the current directory to the given destination.
     *
     * @param arg - the destination directory
     * @return the file path to the destination directory
     */
    @Override
    public File execute(String... arg) {
        return new File(arg[0]);
    }
}
