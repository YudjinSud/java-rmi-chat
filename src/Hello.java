import java.rmi.*;

public  class Hello implements IHello {

	private String message;
 
	public Hello(String s) {
		message = s ;
	}

	public String sayHello() throws RemoteException {
		return message ;
	}
}

