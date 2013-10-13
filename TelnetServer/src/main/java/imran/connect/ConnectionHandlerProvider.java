package imran.connect;

import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Provider of connection handler classes.
 */
public class ConnectionHandlerProvider {

    /**
     * Gets a connection handler for given socket connection.
     *
     * @param socket - the connection socket
     * @return an instance of connection handler
     */
    public ConnectionHandler getConnectionHandler(Socket socket) {
        return new OpenConnectionHandler(socket);
    }
}
