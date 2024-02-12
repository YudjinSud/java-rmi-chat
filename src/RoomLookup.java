import java.rmi.RemoteException;

public class RoomLookup implements IRoomLookup {
    private String roomId;
    private int userCount;


    public RoomLookup(String roomId, int size) {
        this.roomId = roomId;
        this.userCount = size;
    }

    @Override
    public String getRoomId() throws RemoteException {
        return roomId;
    }

    @Override
    public int getUserCount() throws RemoteException {
        return userCount;
    }
}