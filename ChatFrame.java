import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

/**
 * ChatFrame is the GUI of server and client.
 *
 */

public class ChatFrame extends JFrame {

    private String userName;
    private JTextArea history;
    private JTextField msgToBeSent;
    
    /**
     * Construct a ServerFrame.
     */
    public ChatFrame(String userName, String serverHost, int port) {        
        this.userName = userName;
        JPanel infoPanel = new JPanel();
        infoPanel.add(new JLabel(String.format("Server: %s    Port: %s    User: %s", serverHost, port, userName )));
        add(infoPanel, BorderLayout.NORTH);
        JPanel chatPanel = initChatPanel(userName);
        add(chatPanel, BorderLayout.CENTER);
        pack(); 
    }   

    private JPanel initChatPanel(final String nickname){
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());    
        
        JPanel msgPanel = new JPanel();
        msgToBeSent = new JTextField(50);       
        msgToBeSent.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String msg = String.format("%tT %s:    %s", new Date(), nickname, msgToBeSent.getText());
                ChatClient.sendMsg(msg);
                msgToBeSent.setText("");
            }
        });
        msgPanel.add(msgToBeSent);
        chatPanel.add(msgPanel, BorderLayout.NORTH);

        JPanel hisPanel = new JPanel();
        history = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(history);
        scrollPane.setEnabled(false);               
        Border titled = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "chatting history");
        hisPanel.setBorder(titled);
        hisPanel.add(scrollPane);
        chatPanel.add(hisPanel, BorderLayout.CENTER);   
        return chatPanel;
    }
    
    public void showMessage(String msg) {
        history.append(msg+"\n");   
    }   
     
}