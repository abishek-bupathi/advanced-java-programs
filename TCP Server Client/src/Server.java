// Name: Abishek Bupathi

import java.io.*;
import java.net.*;
import java.util.concurrent.*;


public class Server {

    public static void main(String[] args) throws IOException{

        // Initializing network variables and thread pool size
        int port, threadPoolSize = 10;
        InetAddress IPAddr;
        Socket sock;

        // 2D String array to store countries and it's capital
        String[][] countryCap = {
                {"Ireland", "Dublin"}, {"Germany", "Berlin"},
                {"India", "New Delhi"}, {"Canada", "Ottawa"},
                {"USA", "Washington D.C."}, {"Switzerland", "Bern"},
                {"Australia", "Canberra"}, {"France", "Paris"}};

        // Checking if all the values to start the server is provided in the input
            if (args.length == 2) {
                IPAddr = InetAddress.getByName(args[0]);
                port = Integer.parseInt(args[1]);
            } else {
                System.out.println("Incorrect input, try again !");
                return;
            }

            // Create a ServerSocket that binds to all available IP addresses on this device
            ServerSocket servsock = new ServerSocket(port, 0, IPAddr);
            // Message to show that the server is running at the specified port
            System.out.println("Server is running at port: " + port+"\nServer IP Address: "+IPAddr.getHostAddress());

            // Initializing thread pool executor with fixed thread pool size (10)
            ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

            // Infinite while loop that enables server to keep on listening
            while (true) {
                // Accepting queries from clients
                sock = servsock.accept();
                // Executing those queries using separate thread
                executor.execute(new ClientHandler(sock, countryCap));
            }


    }

}
