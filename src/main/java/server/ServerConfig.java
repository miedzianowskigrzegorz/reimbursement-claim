package server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Class responsible for configuring and starting an HTTP server.
 */
public class ServerConfig {

    // The port number to run the server on.
    private static final int PORT = 8080;

    /**
     * Configures and starts the HTTP server with provided handlers.
     *
     * @param handlers A map of context paths to HttpHandlers for different routes.
     */
    public static void configureAndStart(Map<String, HttpHandler> handlers) {
        try {
            // Create an HTTP server instance.
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

            // Register the provided handlers with their corresponding context paths.
            for (Map.Entry<String, HttpHandler> entry : handlers.entrySet()) {
                server.createContext(entry.getKey(), entry.getValue());
            }

            // Set the executor to null, using the default executor.
            server.setExecutor(null);

            // Start the server.
            server.start();

            // Print a message indicating that the server is running.
            System.out.println("Server is running on port " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
