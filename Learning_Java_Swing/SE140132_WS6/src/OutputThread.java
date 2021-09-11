
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khoi Nguyen
 */
public class OutputThread extends Thread {
    
    private final String NETWORK_ERROR = "Network error";
    
    private Socket socket;
    private JTextArea textArea;
    private String sender;
    private String receiver;

    BufferedReader bufferedReader;

    
    public OutputThread(Socket socket, JTextArea textArea, 
                         String sender, String receiver) {
        
        super();
        this.socket = socket;
        this.textArea = textArea;
        this.sender = sender;
        this.receiver = receiver;
    
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, NETWORK_ERROR);
            System.exit(0);
        }
        
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (socket != null) {
                    String msg = "";
                    if ((msg = bufferedReader.readLine()) != null &&
                            msg.length() > 0) {
                        textArea.append("\n" + receiver + ": " + msg);
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
