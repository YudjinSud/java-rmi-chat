import java.rmi.*;

public interface IHello extends Remote {
	public String sayHello()  throws RemoteException;
}
