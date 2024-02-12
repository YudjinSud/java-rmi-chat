import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RoomManager extends UnicastRemoteObject implements IRoomManager {
    private final List<IRoom> allRooms;

    public RoomManager() throws RemoteException {
        allRooms = new ArrayList<>();
    }

    public Room createRoom(String roomId) throws RemoteException {
        Room newRoom = new Room(roomId);
        allRooms.add(newRoom);
        return newRoom;
    }

    public List<IRoom> getAllRooms() throws RemoteException {
        return allRooms;
    }
}
