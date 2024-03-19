package tesr;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ChatServer {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        Logger log = Logger.getLogger(String.valueOf(ChatServer.class));

        int port = 5050;
        int Maximumconnections = 10;
        ServerSocket servidor = null;
        Socket socket = null;
        ChatMessages messages = new ChatMessages();

        try {
            servidor = new ServerSocket(port    , Maximumconnections);

            while (true) {
                log.info("Server waiting for connections.");
                socket = servidor.accept();
                log.info("Client with IP " + socket.getInetAddress().getHostName() + " connected.");

                ClientConnection cc = new ClientConnection(socket, messages);
                cc.start();

            }
        } catch (IOException ex) {
            // log.error("Error: " + ex.getMessage());
        } finally{
            try {
                socket.close();
                servidor.close();
            } catch (IOException ex) {
                // log.error("Error al cerrar el servidor: " + ex.getMessage());
            }
        }
    }
}

