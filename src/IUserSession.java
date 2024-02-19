import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserSession extends Remote {
    public String getUserId() throws RemoteException;
    public void receiveMessage(Message message, Boolean shouldWrite) throws RemoteException;
    public void leave() throws RemoteException;
}
