package imran;

import imran.connect.ConnectionHandler;
import imran.connect.ConnectionHandlerProvider;
import imran.socket.SocketFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static imran.log.Logger.log;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 * <p/>
 * A simple Java server application that supports "telnet"-like connections. A "telnet" like application can be used
 * for testing the server.  The server supports multiple concurrent connections and it responds to very basic commands
 * like "ls", "cd", "mkdir", "pwd". It also is portable across platforms (build time and run time).
 * This does not use the classes that invoke native commands (e.g. Runtime or ProcessBuilder).  This project can be
 * built with Maven 2.
 */
public class TelnetServer {

    /**
     * Port number - (scoped as package private for use in unit test)
     */
    static final int PORT = 2222;

    /**
     * Socket factory to provide server socket
     */
    private final SocketFactory socketFactory;

    /**
     * Connection handler after a connection is accepted
     */
    private final ConnectionHandlerProvider handlerProvider;

    /**
     * The server socket for the given port
     */
    private ServerSocket serverSocket;

    /**
     * Socket for accepted connection
     */
    private Socket acceptedConnection;

    /**
     * Main method to run this class
     *
     * @param args - commandline arguments, if any
     * @throws Exception - if fails to start this class
     */
    public static void main(String args[]) throws Exception {

        try {
            TelnetServer telnetServer = new TelnetServer(new SocketFactory(), new ConnectionHandlerProvider());

            while (true) {
                log("> Waiting to accept a connection on port [" + PORT + "]");
                telnetServer.forServerSocket().accept().connection();
            }
        }
        catch (IOException e) {
            log("> Error starting TelnetServer: [" + e.getMessage() + "]");
        }
    }

    /**
     * Creates an instance of this class for given <code>SocketFactory</code> and
     * <code>ConnectionHandlerProvider</code>. Scoped as package private for unit testing.
     *
     * @param socketFactory  - Socket factory to provide server socket
     * @param connectionHandlerProvider - Connection handler after a connection is accepted
     */
    TelnetServer(SocketFactory socketFactory, ConnectionHandlerProvider connectionHandlerProvider) {
        this.socketFactory = socketFactory;
        this.handlerProvider = connectionHandlerProvider;
    }

    /**
     * Gets a server socket for the given port.  Scoped as package private for unit testing.
     *
     * @return  An instance of this class
     * @throws IOException when fails to get a server socket for the given port
     */
    TelnetServer forServerSocket() throws IOException {
        this.serverSocket = this.socketFactory.getServerSocket(PORT);
        return this;
    }

    /**
     * Accepts a connection from server socket. Scoped as package private for unit testing.
     *
     * @return An instance of this class
     * @throws IOException when fails to accept a connection from the server socket
     */
    TelnetServer accept() throws IOException {
        this.acceptedConnection = serverSocket.accept();
        return this;
    }

    /**
     * Starts a connection thread after a connection is accepted from the server socket.  Scoped as package private
     * for unit testing.
     */
    void connection() {
        ConnectionHandler connectionHandler = handlerProvider.getConnectionHandler(acceptedConnection);
        connectionHandler.startConnection();
    }
}
