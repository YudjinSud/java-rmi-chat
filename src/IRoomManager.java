import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRoomManager extends Remote {
    public IRoom createRoom(String roomId) throws RemoteException;

    public List<IRoom> getAllRooms() throws RemoteException;

    public IRoom findRoom(String roomId) throws RemoteException;

    public void joinRoom(IRoom room, IUserSession userSession) throws RemoteException;
}
