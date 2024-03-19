package tesr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * Una sencilla ventana para configurar el chat
 *
 * @author Ivan Salas Corrales <http://programandoointentandolo.com/>
 */

public class ConfigurationWindow extends JDialog {

    private Logger log = Logger.getLogger(String.valueOf(ConfigurationWindow.class));
    private JTextField tfUser;
    private JTextField tfHost;
    private JTextField tfPort;

    /**
     * Constructor de la ventana de configuracion inicial
     *
     * @param padre Ventana padre
     */

    public ConfigurationWindow(JFrame padre) {
        super(padre, "Initial Configuration", true);

        JLabel lbUsuario = new JLabel("UsER:");
        JLabel lbHost = new JLabel("Host:");
        JLabel lbPuerto = new JLabel("Port:");

        tfUser = new JTextField();
        tfHost = new JTextField("localhost");
        tfPort = new JTextField("5000");

        JButton btAceptar = new JButton("Accepted");
        btAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 0, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        c.add(lbUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(lbHost, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        c.add(lbPuerto, gbc);

        gbc.ipadx = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 1;
        gbc.gridy = 0;
        c.add(tfUser, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(tfHost, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        c.add(tfPort, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        c.add(btAceptar, gbc);

        this.pack();
        this.setLocation(450, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }


    public String getUser(){
        return this.tfUser.getText();
    }

    public String getHost(){
        return this.tfHost.getText();
    }

    public int getPort(){
        return Integer.parseInt(this.tfPort.getText());
    }

}

