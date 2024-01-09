import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {

    private static final String host = "localhost";
    private static final int port = 1900;

    /// RMI-CLIENT
    public static void main(String[] args) {

        /// CREATE USERNAME

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca un nombre de usuario");
        String username = scanner.nextLine();

        MessageBuffer messageBuffer = new MessageBuffer();
        new ChatListenerThread(host, port, username).start();
        while(true) {
            try {
                scanner = new Scanner(System.in);
                String msg = scanner.nextLine();
                ChatInterface access = (ChatInterface) Naming.lookup("rmi://" + host + ":" + port + "/geeksforgeeks");
                access.sendMessage("[" + username + "]: " + msg);
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}