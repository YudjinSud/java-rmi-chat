import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRoomManager extends Remote {
    public Room createRoom(String roomId) throws RemoteException;
    public List<IRoom> getAllRooms() throws RemoteException;
    }
