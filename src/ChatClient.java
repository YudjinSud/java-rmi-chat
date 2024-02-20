import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Usage: java class name <rmiregistry host> <rmiregistry port>");
                return;
            }

            String host = args[0];
            int port = Integer.parseInt(args[1]);

            Registry registry = LocateRegistry.getRegistry(host, port);

            IRoomManager roomManager = (IRoomManager) registry.lookup("RoomManager");
            IMessageStore messageStore = (IMessageStore) registry.lookup("MessageStore");

            new ChatClientCLI(roomManager, messageStore).run();

        } catch (Exception e) {
            System.err.println("Error on client: " + e);
            e.printStackTrace();
        }
    }
}
