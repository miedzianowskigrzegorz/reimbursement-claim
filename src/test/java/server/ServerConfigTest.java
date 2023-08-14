package server;

import com.sun.net.httpserver.HttpHandler;
import handlers.reimbursement.ReimbursementHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServerConfigTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testConfigureAndStart() {
        Map<String, HttpHandler> handlers = new HashMap<>();
        handlers.put("/reimbursement", new ReimbursementHandler());

        ServerConfig.configureAndStart(handlers);

        String output = outputStreamCaptor.toString();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}