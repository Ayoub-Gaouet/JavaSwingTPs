package Socket.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    // rendu exécutable depuis la JVM
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Starting Client...");

            try (Socket s = new Socket("127.0.0.1", 9001);
                 BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 PrintWriter pw = new PrintWriter(s.getOutputStream(), true)) {

                System.out.println("I'm connected to server..");

                // lecture accueil serveur
                String ligne = br.readLine();
                System.out.println(ligne);

                String id = sc.nextLine();

                pw.println(id);

                // chat
                EcritureClient ecritureC = new EcritureClient(pw);
                ecritureC.start();

                LectureClient lectureC = new LectureClient(br);
                lectureC.start();

                // join threads (optionnel) - attendez que les fils se terminent
                try {
                    ecritureC.join();
                    lectureC.join();
                } catch (InterruptedException ignored) {
                }

            } catch (IOException e) {
                System.out.println("erreur coté client " + e.getMessage());
            }

        }

        // System.out.println("End Client..");
    }
}