package Socket;

import Socket.Server.MyClientSocket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SocketsManager {
    public static ArrayList<MyClientSocket> list_of_sockets = new ArrayList<MyClientSocket>();

    public static void diffuserMessage(String idSender, String msg) {
        for (int i = 0; i < list_of_sockets.size(); i++) {
            MyClientSocket mcs = list_of_sockets.get(i);
            if (!mcs.idClient.equalsIgnoreCase(idSender))
                try {
                    PrintWriter pw = new PrintWriter(mcs.s.getOutputStream());
                    pw.println(msg);
                    pw.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        }
    }
}