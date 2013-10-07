import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Client:
 * - Allow users to connect to server.
 * - Accept input text from user and pass it to server
 * - Receive and display dialog from server
 * - Send a message when user disconnects.
 * 
 * start the ChatClient programs on the client hosts.
 *    USAGE: java ChatClient serverHost serverPort user
 *           optional command line arguments:
 *               [serverHost]: the IP or host name of the server to be connected to, "localhost" by default.
 *               [serverPort]: the port number the server is listening to, 8000 by default.
 *               [user]: the user using the chatting client, "anonymous_user" by default.
 *
 *NOTE: The client will fail to connect if the server is not turned on or wrong server host and port number are given.
 *
 * @author: Yu Jin
 */


public class ChatClient{

    public static final String DEFAULT_SERVER = "localhost";
    public static final String DEFAULT_USER = "anonymous_client";
    
    private static ChatFrame frame;
    private static String user;
    private static Socket serverSocket;

    public static void main(String[] args)  throws InterruptedException, IOException {
        final String server = (args.length > 0) ? args[0] : DEFAULT_SERVER;
        final int port = (args.length > 1) ? Integer.parseInt(args[1]) : ChatServer.DEFAULT_PORT;   
        user = (args.length > 2) ? args[2] : DEFAULT_USER;
        connect2Server(server, port);   

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChatClient.frame = new ChatFrame(user, server, port);
                ChatClient.frame.setTitle("Chatting Client");
                ChatClient.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ChatClient.frame.setVisible(true);                     
            }
        });    

        startChatting();            
    }
    
    /**
     * Start a thread to:
     * 1. connect to the specified server host on the specified port,
     * 2. and then keep processing incoming message from the server.
     * @param server the host name or IP of the server to be connected.
     * @param port the port number on which the server is listening.
     */  
     public static void connect2Server(String server, int port) throws IOException{
        serverSocket = new Socket();
        serverSocket.connect(new InetSocketAddress(server, port), 10000 );        
        PrintWriter output = new PrintWriter(serverSocket.getOutputStream(), true);
        output.println(user);                        
    }

    private static void startChatting(){
        try{
            Scanner incomingMsg = new Scanner(serverSocket.getInputStream());
            while (incomingMsg.hasNextLine()){
                String msg = incomingMsg.nextLine();
                if(ChatClient.frame != null)
                    ChatClient.frame.showMessage(msg);
            }
        }
        catch(IOException e){ 
        }
        finally{
            try{
                if(serverSocket != null)
                    serverSocket.close();
                    serverSocket  = null;
            }
            catch(IOException e){
            }
        }
    }
    
    public static void sendMsg(String msg) { 
        if(serverSocket != null){
            try{
                PrintWriter output = new PrintWriter(serverSocket.getOutputStream(), true);
                output.println(msg);     
            }
            catch(IOException e){
                System.err.println("Failed to send message! Please be sure the server is still alive.");
            }
        }
    }
}