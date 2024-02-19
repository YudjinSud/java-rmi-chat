import java.io.Serializable;
import java.rmi.RemoteException;

public class UserSession implements IUserSession, Serializable {
    private final String userId;
    private final IRoom room;

    private IMessageStore messageStore;

    public UserSession(String userId, IRoom room, IMessageStore messageStore) {
        this.userId = userId;
        this.room = room;
        this.messageStore = messageStore;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void receiveMessage(Message message, Boolean shouldWrite) throws RemoteException {
        System.out.println("Received message from " + message.getSender() + " in room " + message.getRoomId() + ": " + message.getContent());

        if (shouldWrite) {
            this.messageStore.writeToFile(message);
        }
    }

    @Override
    public void leave() throws RemoteException {
        room.leave(this);
    }
}
