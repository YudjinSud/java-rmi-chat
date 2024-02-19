import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RoomManager implements IRoomManager, Remote {
    private final List<IRoom> allRooms;

    public RoomManager() throws RemoteException {
        allRooms = new ArrayList<>();
    }

    public IRoom createRoom(String roomId) throws RemoteException {
        Room newRoom = new Room(roomId);
        IRoom roomStub = (IRoom) UnicastRemoteObject.exportObject(newRoom, 0);
        allRooms.add(roomStub);
        return roomStub;
    }

    public List<IRoom> getAllRooms() throws RemoteException {
        return allRooms;
    }

    @Override
    public IRoom findRoom(String roomId) throws RemoteException {
        for (IRoom room : allRooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }

        return null;
    }

    @Override
    public void joinRoom(IRoom room, IUserSession userSession) throws RemoteException {
        room.addUserSession(userSession);
    }
}
