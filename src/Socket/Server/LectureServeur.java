package Socket.Server;

import java.io.BufferedReader;
import java.io.IOException;

import Socket.SocketsManager;

public class LectureServeur extends Thread {
    BufferedReader br;
    String idSender;

    LectureServeur(BufferedReader br, String idSender) {
        this.br = br;
        this.idSender = idSender;
    }

    @Override
    public void run() {
        while (true) {
            String msg = null;
            try {
                msg = br.readLine();

                SocketsManager.diffuserMessage(idSender, msg);

                // System.out.println(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
