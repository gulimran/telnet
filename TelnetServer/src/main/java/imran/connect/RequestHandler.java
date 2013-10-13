package imran.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Request handler for a client connected at the given socket.
 */
public class RequestHandler {

    /**
     * The socket for the connected client
     */
    private Socket socket;

    /**
     * The reader to receive input from the client
     */
    private BufferedReader in;

    /**
     * Creates an instance of this class for the given socket connection.
     *
     * @param socket - the socket for the connected client
     */
    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Sets up a reader to receive input from the connected client.
     *
     * @throws IOException when fails to initialise the reader
     */
    public void initialize() throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Gets a line of input from the client.
     *
     * @return a line of data input by client as string
     * @throws IOException when fails to get a line of input
     */
    public String getLine() throws IOException {
        return in.readLine();
    }

    /* Package private for unit testing dependency injection. */
    void setReader(BufferedReader reader) {
        this.in = reader;
    }
}
