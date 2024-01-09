import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChatListenerThread extends Thread{

    public ChatListenerThread() {
    }

    @Override
    public void run() {
        while(true) {
            try {
                ChatInterface access = (ChatInterface) Naming.lookup("rmi://localhost:1900" + "/geeksforgeeks");
                String msg = access.fetchMessage();
                System.out.println(msg);
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
