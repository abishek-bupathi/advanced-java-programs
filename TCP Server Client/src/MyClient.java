// Name: Abishek Bupathi

import java.io.*;
import java.net.*;

public class MyClient {

    public static void main(String[] args)throws IOException{

        // Initializing the Network variables
        int port;
        String country;
        InetAddress IPAddr;

        Socket sock;

        try {
            // Checking if the command line input contains all the the required values (Country name, IP Address and Port number)
            if (args.length == 3) {
                // Assigning the values from the input to the corresponding variables
                country = args[0];
                IPAddr = InetAddress.getByName(args[1]);
                port = Integer.parseInt(args[2]);
            } else {
                // When the input format is incorrect warning message is shown and the program terminates
                System.out.println("Incorrect input, try again !");
                return;
            }

            // Initializing socket for TCP communication
            sock = new Socket(IPAddr, port);

            // Initializing Output and Input for TCP Communication
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintStream out = new PrintStream(sock.getOutputStream());
            // Sending the country name to the server
            out.println(country);
            // Reading the message received from the server
            String fromServer = in.readLine();

            System.out.println("Capital of " + country + " is " + fromServer);

            // Closing the Input and Output connections
            out.close();
            in.close();
            sock.close();

        } catch (ConnectException e) {
            System.out.println("Connection refused, try again !");
        }
    }
}
