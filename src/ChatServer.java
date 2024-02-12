import java.rmi.RemoteException;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;

public class ChatServer {


    public static void main(String[] args) {
        try {
            // Create a tp2.Hello remote object
            Hello h = new Hello("tp2.Hello world !");
            IHello h_stub = (IHello) UnicastRemoteObject.exportObject(h, 0);

            RoomManager manager = new RoomManager();
            IRoomManager manager_stub = (IRoomManager) UnicastRemoteObject.exportObject(manager,0);


            // Register the remote object in RMI registry with a given identifier
            Registry registry = null;
            if (args.length > 0)
                registry = LocateRegistry.getRegistry(Integer.parseInt(args[0]));
            else
                registry = LocateRegistry.getRegistry();
            registry.rebind("HelloService", h_stub);
            registry.rebind("RoomManager", manager_stub);


            System.out.println("Server ready");

        } catch (Exception e) {
            System.err.println("Error on server :" + e);
            e.printStackTrace();
        }
    }
}
