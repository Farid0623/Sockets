import tesr.ConfigurationWindow;
import tesr.ConnectionServer;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class ChatClient extends JFrame {

    private Logger log = Logger.getLogger(String.valueOf(ChatClient.class));
    private JTextArea Chatmessages;
    private Socket socket;

    private int port;
    private String host;
    private String user;

    public ChatClient(){
        super("CHAT Cliente");


        Chatmessages = new JTextArea();
        Chatmessages.setEnabled(false);
        Chatmessages.setLineWrap(true);
        Chatmessages.setWrapStyleWord(true);
        JScrollPane scrollMensajesChat = new JScrollPane(Chatmessages);
        JTextField tfMensaje = new JTextField("");
        JButton btEnviar = new JButton("Send");



        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(40, 40, 40, 40);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        c.add(scrollMensajesChat, gbc);
        gbc.gridwidth = 1;
        gbc.weighty = 0;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(tfMensaje, gbc);
        gbc.weightx = 0;

        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(btEnviar, gbc);

        this.setBounds(400, 100, 400, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ConfigurationWindow vc = new ConfigurationWindow(this);
        host = vc.getHost();
        port = vc.getPort();
        user = vc.getUser();

        log.info("Quieres conectarte a " + host + " en el puerto " + port + " con el nombre de ususario: " + user + ".");

        try {
            socket = new Socket(host, port);
        } catch (UnknownHostException ex) {
            //  log.error("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        } catch (IOException ex) {
        }

        btEnviar.addActionListener(new ConnectionServer(socket, tfMensaje, user));

    }

    /**
     */

    public void recibirMensajesServidor(){
        DataInputStream entradaDatos = null;
        String mensaje;
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
        } catch (NullPointerException ex) {
        }

        boolean conectado = true;
        while (conectado) {
            try {
                mensaje = entradaDatos.readUTF();
                Chatmessages.append(mensaje + System.lineSeparator());
            } catch (IOException ex) {

                conectado = false;
            } catch (NullPointerException ex) {

                conectado = false;
            }
        }
    }

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        ChatClient c = new ChatClient();
        c.recibirMensajesServidor();
    }

}

