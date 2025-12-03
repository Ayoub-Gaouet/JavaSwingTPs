package Socket.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    // rendu exécutable depuis la JVM
    public static void main(String[] args) {

        int nbClients = 0;

        System.out.println("Starting server...");

        try (ServerSocket server = new ServerSocket(9001)) {

            while (nbClients < 3) {
                System.out.println("server waiting..");
                Socket s = server.accept();
                System.out.println("client accepted!");
                nbClients++;

                Discussion disc = new Discussion(s);
                disc.start();
            }

        } catch (IOException e) {
            System.out.println("erreur Serveur " + e.getMessage());
        }

        System.out.println("End Server..");
    }
}
