import java.rmi.RemoteException;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatServer {
    public static void main(String[] args) {
        try {
            RoomManager manager = new RoomManager();
            IRoomManager manager_stub = (IRoomManager) UnicastRemoteObject.exportObject(manager, 0);

            MessageStore messageStore = new MessageStore("src/messages.txt");
            IMessageStore messageStore_stub = (IMessageStore) UnicastRemoteObject.exportObject(messageStore, 0);

            Registry registry = null;
            if (args.length > 0)
                registry = LocateRegistry.getRegistry(Integer.parseInt(args[0]));
            else
                registry = LocateRegistry.getRegistry();
            registry.rebind("RoomManager", manager_stub);
            registry.rebind("MessageStore", messageStore_stub);

            System.out.println("Server ready");

            manager_stub.createRoom("f");
            manager_stub.createRoom("s");

            List<Message> messages = messageStore.readFromFile();

            for (Message message : messages) {
                System.out.println(message.getSender() + " " + message.getRoomId() + " " + message.getContent());
            }

            manager_stub.getAllRooms().forEach(room -> {
                messages.forEach(message -> {
                    try {
                        if (Objects.equals(message.getRoomId(), room.getRoomId())) {
                            room.addMessage(message);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });

            });


        } catch (Exception e) {
            System.err.println("Error on server :" + e);
            e.printStackTrace();
        }
    }
}
