import java.rmi.RemoteException;
import java.util.ArrayList;

public class Room implements IRoom {
    private final String roomId;

    public Room(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    private final ArrayList<String> userList = new ArrayList<String>();
    private final ArrayList<String> messageList = new ArrayList<String>();

    @Override
    public int getUserCount() throws RemoteException {
        return userList.size();
    }

    public void connect(String userId) throws RemoteException {
        userList.add(userId);
        this.broadcastMessage("User " + userId + " has joined the room");
    }

    public void leave(String userId) throws RemoteException {
        userList.remove(userId);
        this.broadcastMessage("User " + userId + " has left the room");
    }

    public void sendMessage(String userId, String message) {
        messageList.add(userId + " : " + message);
    }

    @Override
    public void broadcastMessage(String message) throws RemoteException {
        for (String user : userList) {
            sendMessage(user, message);
        }
    }
}
