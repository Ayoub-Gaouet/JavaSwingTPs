package Socket.Client;

import java.io.PrintWriter;
import java.util.Scanner;

public class EcritureClient extends Thread {
    PrintWriter pw;

    EcritureClient(PrintWriter pw) {
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
