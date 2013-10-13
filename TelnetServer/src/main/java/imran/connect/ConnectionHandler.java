package imran.connect;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * An interface for the connection handler.
 */
public interface ConnectionHandler extends Runnable {

    /**
     * Start connection handler in a new thread.
     *
     * @return the thread that is started to handle the connection
     */
    Thread startConnection();
}
