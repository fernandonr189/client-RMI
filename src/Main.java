import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {

    /// RMI-CLIENT
    public static void main(String[] args) {
        MessageBuffer messageBuffer = new MessageBuffer();
        ChatInterface access = null;
        try {
            access = (ChatInterface) Naming.lookup("rmi://localhost:1900" + "/geeksforgeeks");
            access.registerClient();
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
        new ChatListenerThread(messageBuffer).start();
        while(true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String msg = scanner.nextLine();
                access = (ChatInterface) Naming.lookup("rmi://localhost:1900" + "/geeksforgeeks");
                access.sendMessage(msg);
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}