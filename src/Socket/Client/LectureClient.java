package Socket.Client;

import java.io.BufferedReader;
import java.io.IOException;

public class LectureClient extends Thread {
    BufferedReader br;

    LectureClient(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        while (true) {
            String msg = null;
            try {
                msg = br.readLine();
                System.out.println(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
