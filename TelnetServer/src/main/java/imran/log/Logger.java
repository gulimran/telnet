package imran.log;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Logger to write log messages to the System.out.
 */
public class Logger {

    /**
     * Writes a log message to System.out.
     *
     * @param message - the message to be logged
     */
    public static void log(String message) {
        System.out.println(message);
    }

    /**
     * Writes a log message and error message to the System.out and prints stack trace to assist debugging.
     *
     * @param message - the message to be logged
     * @param throwable - the exception or error
     */
    public static void log(String message, Throwable throwable) {
        System.out.println(message + " Error: [" + throwable.getMessage() + "]");
        throwable.printStackTrace();
    }
}
