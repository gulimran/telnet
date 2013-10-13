package imran.command;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Provides an instance of supported commands.
 */
public class CommandProvider {

    /**
     * Supported commands.
     */
    public enum SupportedCommands {
        PWD,
        CD,
        LS,
        MKDIR;
    };

    /**
     * Gets an instance of supported command for the given command type and using the current directory path.
     *
     * @param aCommand - a command type
     * @param currentDir - the current directory path file
     * @return the command instance
     */
    public Command get(SupportedCommands aCommand, File currentDir) {
        Command command = null;

        switch (aCommand) {
            case PWD:
                command = new PwdCommand(currentDir);
                break;
            case CD:
                command = new CdCommand(currentDir);
                break;
            case LS:
                command = new LsCommand(currentDir);
                break;
            case MKDIR:
                command = new MkdirCommand(currentDir);
                break;
            default:
                // in case if some one adds a new supported command but fails to add a case for it in this block
                command = new BaseCommand(currentDir);
                break;
        }

        return command;
    }
}
