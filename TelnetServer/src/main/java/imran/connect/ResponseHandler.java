package imran.connect;

import java.io.*;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Response handler for a client connected at the given socket.
 */
public class ResponseHandler {

    /**
     * The socket for the connected client
     */
    private Socket socket;

    /**
     * The writer to push output from server to the client
     */
    private PrintWriter out;

    /**
     * Creates an instance of this class for the given socket connection.
     *
     * @param socket - the socket for the connected client
     */
    public ResponseHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Sets up a writer to push output to the connected client.
     *
     * @throws IOException when fails to initialise the writer
     */
    public void initialize() throws IOException {
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Prints a line of message to the output to the connected client.
     *
     * @param message - the message to be printed to the client
     * @throws IOException when fails to print message to the client
     */
    public void setLine(String message) throws IOException {
        out.println(message);
    }

    /* Package private for unit testing dependency injection. */
    void setWriter(PrintWriter writer) {
        this.out = writer;
    }
}
