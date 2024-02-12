import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRoomLookup extends Remote {
    String getRoomId() throws RemoteException;
    int getUserCount() throws RemoteException;

}
