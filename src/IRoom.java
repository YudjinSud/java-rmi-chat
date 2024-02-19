import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IRoom extends Remote {
    public String getRoomId() throws RemoteException;

    public int getUserCount() throws RemoteException;

    public void addUserSession(IUserSession userSession) throws RemoteException;

    public void sendMessageFrom(IUserSession from, String message) throws RemoteException;

    public void leave(IUserSession userSession) throws RemoteException;

    public void broadcastMessage(String message, Boolean shouldWrite) throws RemoteException;

    public void addMessage(Message message) throws RemoteException;
}
