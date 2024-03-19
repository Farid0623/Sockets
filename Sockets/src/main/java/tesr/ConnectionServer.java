package tesr;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionServer  implements ActionListener {

    private Logger log = Logger.getLogger(String.valueOf(ConnectionServer.class));
    private Socket socket;
    private JTextField tfMessage;
    private String user;
    private DataOutputStream DataOutput;

    public ConnectionServer(Socket socket, JTextField tfMessage, String user) {
        this.socket = socket;
        this.tfMessage = tfMessage;
        this.user = user    ;
        try {
            this.DataOutput = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
        } catch (NullPointerException ex) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DataOutput.writeUTF(user + ": " + tfMessage.getText() );
            tfMessage.setText("");
        } catch (IOException ex) {
        }
    }
}

