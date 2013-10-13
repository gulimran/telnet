package imran.command;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Creates a sub-directory in the current directory.
 */
public class MkdirCommand extends BaseCommand {

    /**
     * Creates an instance of make directory command for the given current directory path.
     *
     * @param currentPath - the current directory path file
     */
    public MkdirCommand(File currentPath) {
        super(currentPath);
    }

    /**
     * Creates a sub-directory in the current directory of this class.
     *
     * @param arg - the sub-directory to be created
     * @return path file to the current directory
     * @throws IOException when fails to create the sub-directory for the given argument
     */
    @Override
    public File execute(String... arg) throws IOException {
        File newDir = new File(currentPath.getCanonicalPath() + File.separator + arg[0]);
        newDir.mkdir();

        return currentPath;
    }
}
