import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Room implements IRoom, Serializable {
    private final String roomId;

    public Room(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    private final ArrayList<IUserSession> userList = new ArrayList<IUserSession>();
    private final ArrayList<IMessage> messageList = new ArrayList<IMessage>();

    public void addMessage(Message message) {
        messageList.add(message);
    }

    @Override
    public int getUserCount() throws RemoteException {
        return userList.size();
    }

    @Override
    public void addUserSession(IUserSession userSession) throws RemoteException {
        userList.add(userSession);

        if (this.messageList.size() > 0) {
            int count = 0;
            userSession.receiveMessage(new Message(this.roomId, this.roomId, "Welcome to the room! Last 10 messages:"), false);
            for (IMessage message : messageList) {
                if (count > 10) break;
                userSession.receiveMessage((Message) message, false);

                count++;
            }
        }

        this.broadcastMessage("User " + userSession.getUserId() + " has joined the room", false);
    }

    public void leave(IUserSession userSession) throws RemoteException {
        userList.remove(userSession);
        this.broadcastMessage("User " + userSession.getUserId() + " has left the room", false);
    }

    public void sendMessageTo(IUserSession to, String message, Boolean shouldWrite) throws RemoteException {
        Message msg = new Message(this.roomId, this.roomId, message);
        if (shouldWrite) {
            messageList.add(msg);
        }
        to.receiveMessage(msg, shouldWrite);
    }

    public void sendMessageFrom(IUserSession from, String message) throws RemoteException {
        Message msg = new Message(from.getUserId(), this.roomId, message);

        messageList.add(msg);
        for (IUserSession user : userList) {
            if (user != from) {
                user.receiveMessage(msg, true);
            }
        }
    }

    @Override
    public void broadcastMessage(String message, Boolean shouldWrite) throws RemoteException {
        for (IUserSession user : userList) {
            sendMessageTo(user, message, shouldWrite);
        }
    }
}
