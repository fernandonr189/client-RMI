import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    public void sendMessage(String msg) throws RemoteException;
    public String fetchMessage() throws RemoteException;

}