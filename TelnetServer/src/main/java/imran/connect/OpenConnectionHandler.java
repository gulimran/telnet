package imran.connect;

import imran.command.*;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import static imran.log.Logger.log;
import static imran.command.CommandProvider.SupportedCommands.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gul Imran
 * Date: 05/03/13
 *
 * Connection handler class for open (non-authenticated) client connection.  This class does not challenge a connection
 * with the user id and password or any other means of authentication.
 */
public class OpenConnectionHandler implements ConnectionHandler {

    /**
     * The welcome message when the connection is first established with a client.
     *
     */
    static final String WELCOME_MSG = "> Welcome to Imran telnet server.";

    /**
     * The connected from message.
     */
    static final String CONNECTED_FROM_MSG = "> You are connected from ";

    /**
     * Error message when a given command is not recognised
     */
    static final String ERROR_UNKNOWN_COMMAND = "> Not recognized as an internal or external command ";

    /**
     * The client socket connection
     */
    private final Socket socket;

    /**
     * The command provider
     */
    private CommandProvider commandProvider;

    /**
     * The request handler to receive input from the client
     */
    private RequestHandler requestHandler;

    /**
     * The response handler for output to the client
     */
    private ResponseHandler responseHandler;

    /**
     * Creates an instance of this class for the given socket a client connection.
     *
     * @param socketConnection - the socket connection for the client
     */
    public OpenConnectionHandler(Socket socketConnection) {
        this.socket = socketConnection;
        this.commandProvider = new CommandProvider();
        this.requestHandler = new RequestHandler(socket);
        this.responseHandler = new ResponseHandler(socket);
    }

    /**
     * Starts a new connection thread for this class.
     *
     * @return the thread that was started for this connection
     */
    public Thread startConnection() {
        Thread t = new Thread(this);
        t.start();
        return t;
    }

    /* non-javadoc - @see java.lang.Runnable#run() */
    @Override
    public void run() {
      try
        {
            // initialize the request and response handlers with respective input and output streams
            requestHandler.initialize();
            responseHandler.initialize();

            // send welcome message to the client
            responseHandler.setLine(WELCOME_MSG);
            responseHandler.setLine(CONNECTED_FROM_MSG + socket.getInetAddress().getHostName());

            // create a file path to the current directory on the server
            File currentDir = new File(System.getProperty("user.dir") != null ? System.getProperty("user.dir"): "/tmp");

            // handle command requests
            while (true) {
                String line = requestHandler.getLine().trim();

                if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit")) {
                    break;
                }
                else if (line.equalsIgnoreCase("pwd")) {
                    responseHandler.setLine(commandProvider.get(PWD, currentDir).execute());
                }
                else if (line.startsWith("cd")) {
                    File newPath = commandProvider.get(CD, currentDir).execute(line.split(" ")[1]);
                    responseHandler.setLine(getPath(newPath));
                    currentDir = newPath;
                }
                 else if (line.equalsIgnoreCase("ls")) {
                    responseHandler.setLine(commandProvider.get(LS, currentDir).execute());
                }
                else if (line.startsWith("mkdir")) {
                    File newPath = commandProvider.get(MKDIR, currentDir).execute(line.split(" ")[1]);
                    responseHandler.setLine(getPath(currentDir));
                }
                else {
                    responseHandler.setLine(ERROR_UNKNOWN_COMMAND + line);
                }
            }
        }
        catch (IOException e) {
            log("> Connection to the client failed.", e);
        }
        finally {
          try {
              socket.close();
          } catch (IOException e) {
              log("> Failed to close socket.", e);
          }
      }
    }

    /**
     * Gets canonical file path to the given file.
     *
     * @param file - a file path to a directory
     * @return canonical file path to the given file or empty string if the given file is null
     * @throws IOException when fails to find the canonical file path for the given file
     */
    private String getPath(File file) throws IOException {
        return file == null ? "" : file.getCanonicalPath();
    }


    /* Package private for unit testing dependency injection. */
    void setCommandProvider(CommandProvider commandProvider) {
        this.commandProvider = commandProvider;
    }

    /* Package private for unit testing dependency injection. */
    void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    /* Package private for unit testing dependency injection. */
    void setResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }
}
