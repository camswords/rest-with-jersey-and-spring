import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebServer {

    public static void main(String[] args) throws Exception {
        new WebServer().runForever();
    }

    private void runForever() throws Exception {
        Server server = new Server(8080);
        server.setHandler(new WebAppContext("web", "/"));
        server.start();
        server.join();
    }
}
