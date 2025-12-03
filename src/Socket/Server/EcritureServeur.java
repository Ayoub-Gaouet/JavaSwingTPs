package Socket.Server;

import java.io.PrintWriter;
import java.util.Scanner;

public class EcritureServeur extends Thread {
    PrintWriter pw;

    EcritureServeur(PrintWriter pw) {
        this.pw = pw;
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            String newMsg = sc.nextLine();
            pw.println(newMsg);
            pw.flush();
        }
    }
}
