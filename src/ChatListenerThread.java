import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChatListenerThread extends Thread{
    private final int port;
    private final String host;

    private final String username;
    public ChatListenerThread(String host, int port, String username) {
        this.host = host;
        this.port = port;
        this.username = username;
    }

    @Override
    public void run() {
        while(true) {
            try {
                ChatInterface access = (ChatInterface) Naming.lookup("rmi://" + host + ":" + port + "/geeksforgeeks");
                String msg = access.fetchMessage();
                if(!msg.contains("[" + username + "]")) {
                    System.out.println(msg);
                }
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
