Network_Chat_Room
=================

1:N Server-Client ChatRoom using multi-threading

Focus:

Concurrency
Networking
GUI
Overview

Write a chat room server and client.

Server

The chat server's primary job is to pass messages from all clients to all clients.
The server will need to
allow connections from clients.
Get a "handle" for the client
receive messages
send the messages to all connected clients.
Client

Allow users to connect to server.
Accept input text from user and pass it to server
Receive and display dialog from server
Send a message when user disconnects.
Options

Maintain registry of users. Provide registration.
Have multiple chat rooms
Log chat room dialogs
Notes

In order for the server to know that the client is "signing off", the client should send a message to the server saying so.

If the client closes the socket without informing the server, then the next time the server attempts a read, it should detect the end-of-file. Similarly, a write should result in an error.

The methods isClosed and isConnected will not work to determine if the connection has been terminated by the other end.
