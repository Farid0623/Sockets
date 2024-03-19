package tesr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class ClientConnection extends Thread implements Observer {

    private Logger log = Logger.getLogger(String.valueOf(ClientConnection.class));
    private Socket socket;
    private ChatMessages messages;
    private DataInputStream DataEntry;
    private DataOutputStream DataOutput;

    public ClientConnection(Socket socket, ChatMessages messages) {
        this.socket = socket;
        this.messages = messages;

        try {
            DataEntry = new DataInputStream(socket.getInputStream());
            DataOutput = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
        }
    }

    @Override
    public void run() {
        String mensajeRecibido;
        boolean conectado = true;
        messages.addObserver(this);

        while (conectado) {
            try {
                mensajeRecibido = DataEntry.readUTF();
                messages.setMessage(mensajeRecibido);
            } catch (IOException ex) {
                log.info("Cliente con la IP " + socket.getInetAddress().getHostName() + " disconnected.");
                conectado = false;
                try {
                    DataEntry.close();
                    DataOutput.close();
                } catch (IOException ex2) {
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            DataOutput.writeUTF(arg.toString());
        } catch (IOException ex) {
        }
    }
}

