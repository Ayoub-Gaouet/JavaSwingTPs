package Socket.Server;

import java.net.Socket;

public class MyClientSocket {
    public String idClient;
    public Socket s;

    public MyClientSocket(String idClient, Socket s) {
        this.idClient = idClient;
        this.s = s;
    }
}
