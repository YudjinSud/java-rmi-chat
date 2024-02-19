import java.rmi.Remote;
import java.rmi.RemoteException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IMessageStore extends Remote {
    public void writeToFile(Message message) throws RemoteException;
    public List<Message> readFromFile() throws RemoteException;
}

