import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IRoom extends Remote {
    public int getUserCount() throws RemoteException;
    public void connect(String userId) throws RemoteException;
    public void leave(String userId) throws RemoteException;
    public void sendMessage(String userId, String message) throws RemoteException;
    public void broadcastMessage(String message) throws RemoteException;
}
