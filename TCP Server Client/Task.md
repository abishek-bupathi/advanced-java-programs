Create command line client and server applications where the server will respond with the name of the capital city of a country if it is passed the name of a country by the client e.g. sending the string "Ireland" from the client to the server would result in the server returning the string "Dublin" to the client. If the server does not recognise the string as a country it kn ows the answer for, then it should just return "Unknown" as the response. The application should be implemented using TCP (Stream) type sockets. When a client connects then the server should handle the connection and data exchange with the client using a TCP socket. The server should be multi-threaded, the main thread of execution should listening for and accept new TCP connections. It should also use a fixed sized thread pool to then handle the subsequent client interaction.

The client should have a command line argument that allows the user to specify the name of the country and the IP address and port number of the server.

Running the client program at the command line to find out the capital city of Spain, to a server listening on TCP port 4400 on the localhost, would then look like this:

c:\java myclient Spain 127.0.0.1 4400

The client should then print out the response.

The server can be programmed to recognise a small number of countries for testing purposes.