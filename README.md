Network_Chat_Room
=================

1:N Server-Client ChatRoom using multi-threading

/**
 * This GUI program models a client in a Server-client architechture based chatting service.
 * To use this tool, please follow the instructions as follows:
 *
 * 1. start the ChatServer program on the server host.
 *    USAGE: java ChatServer port user
 *           optional command line arguments:
 *               [port]: the port number to listen and accept the incoming connection request, 8000 by default.
 *               [user]: the user using the chatting server, "anonymous_user" by default.
 *
 * 2. start the ChatClient program on the client host.
 *    USAGE: java ChatClient serverHost serverPort user
 *           optional command line arguments:
 *               [serverHost]: the IP or host name of the server to be connected to, "localhost" by default.
 *               [serverPort]: the port number the server is listening to, 8000 by default.
 *               [user]: the user using the chatting client, "anonymous_user" by default.
 *
 * 3. Once the two parts are set up, the two users can send message to each other. The chatting history are also
 *    displayed in the text area below the message typing box.
 * 
 * NOTE: The client will fail to connect if the server is not turned on or wrong server host and port number are given.
 *
 *@author: Yu Jin
 */
