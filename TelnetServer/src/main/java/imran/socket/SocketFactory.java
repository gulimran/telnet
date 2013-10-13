package imran.socket;

import java.io.IOException;
import java.net.ServerSocket;

import static imran.log.Logger.log;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * The factory class to provide server socket for the given port number.
 */
public class SocketFactory {

    /**
     * The server socket
     */
    private ServerSocket serverSocket;

    /**
     * Gets a server socket for the given port.
     *
     * @param port - the port number
     * @return the server socket
     * @throws IOException when fails to create the server socket
     */
    public ServerSocket getServerSocket(int port) throws IOException {
        if (serverSocket == null) {
            serverSocket = new ServerSocket(port);
        }

        return serverSocket;
    }

    /* non-javadoc - @see java.lang.Object#finalize */
    @Override
    public void finalize() {
        try {
            serverSocket.close();
            super.finalize();
        }
        catch (Throwable t) {
            log("> Error closing server socket.", t);
        }
    }
}
