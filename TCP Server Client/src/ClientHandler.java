// Name: Abishek Bupathi

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{

    // Declaring instance variables
    String countryCap[][], capital;
    private final Socket sock;

    public ClientHandler(Socket sock, String[][] countryCap) {
        this.sock = sock;
        this.countryCap = countryCap;
        capital = "Unknown";
    }

    public void run(){
    try {

        PrintStream out = new PrintStream(sock.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        // Reading the message (country name) from the Client
        String country = in.readLine();
        // Displaying received message for debugging
        System.out.println("Incoming query: "+country);

        // Looping through the 2D array with countries and Capitals
        for (String[] c: countryCap){
            // If the matching country is found then capital gets the value from the array
            if(c[0].equalsIgnoreCase(country))
                capital = c[1];
        }
        // Sending the message back to the client
        out.println(capital);

        sock.close();

    }catch (IOException e){
        e.printStackTrace();
    }
    }
}
