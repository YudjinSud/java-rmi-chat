import java.io.*;
import java.util.List;

public class Message implements IMessage, Serializable {
    private final String from;
    private final String roomId;
    private final String content;

    public Message(String sender, String roomId, String content) {
        this.from = sender;
        this.roomId = roomId;
        this.content = content;
    }

    public String getSender() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public String getRoomId() {
        return roomId;
    }

}
