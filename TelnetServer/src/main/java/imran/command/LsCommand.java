package imran.command;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Lists files in the current directory.
 */
public class LsCommand extends BaseCommand {

    /**
     * Creates an instance of file list in current directory command for given current file path.
     *
     * @param currentPath - the current file path
     */
    public LsCommand(File currentPath) {
        super(currentPath);
    }

    /**
     * Lists files in the current directory for the the directory path of this class.
     *
     * @return  lisy of files in the current directory
     * @throws IOException when fails to list files in the current directory
     */
    @Override
    public String execute() throws IOException {
        return Arrays.asList(currentPath.list()).toString();
    }
}
