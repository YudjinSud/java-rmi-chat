import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;

public class ChatClient {
    public static void main(String[] args) {



        try {
            if (args.length < 2) {
                System.out.println("Usage: java tp2.HelloClient <rmiregistry host> <rmiregistry port>");
                return;
            }

            String host = args[0];
            int port = Integer.parseInt(args[1]);

            Registry registry = LocateRegistry.getRegistry(host, port);
            IHello h = (IHello) registry.lookup("HelloService");
            IRoomManager room = (IRoomManager) registry.lookup("RoomManager");


        // Remote method invocation
            String res = h.sayHello();
            List<IRoom> list = room.getAllRooms();
            System.out.println(res);

        } catch (Exception e) {
		    System.err.println("Error on client: " + e);
            e.printStackTrace();
        }
    }
}
