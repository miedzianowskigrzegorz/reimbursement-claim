import com.sun.net.httpserver.HttpHandler;

import server.HandlerFactory;
import server.ServerConfig;

import java.util.Map;

/**
 * Main class for the Reimbursement application.
 */
public class ReimbursementApp {

    public static void main(String[] args) {
        Map<String, HttpHandler> handlers = HandlerFactory.createHandlersMap();
        ServerConfig.configureAndStart(handlers);
    }
}

