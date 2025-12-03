package Socket.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Socket.SocketsManager;

public class Discussion extends Thread {
    Socket s;

    Discussion(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        try {
            // ouverture du socket en mode ecriture/output
            PrintWriter pw = new PrintWriter(s.getOutputStream());

            pw.println("envoyer moi votre id!=");
            pw.flush();

            // ouverture du socket en mode lecture/input
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String id = br.readLine();
            System.out.println("id client : " + id);

            SocketsManager.list_of_sockets.add(new MyClientSocket(id, s));
            // chat
            LectureServeur lectureS = new LectureServeur(br, id);
            lectureS.start();

            // EcritureServeur ecritureS = new EcritureServeur(pw);
            // ecritureS.start();

        } catch (Exception e) {
            System.out.println("erreur Discussion " + e.getMessage());
        }
    }
}
