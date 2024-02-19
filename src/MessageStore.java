import java.rmi.Remote;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class MessageStore implements IMessageStore {
    private String filename;

    public MessageStore(String filename) {
        this.filename = filename;
    }

    public synchronized void writeToFile(Message message) {
        try (FileWriter writer = new FileWriter(new File(filename), true);
             PrintWriter printWriter = new PrintWriter(writer)) {

            printWriter.println(messageToCSV(message));

        } catch (IOException e) {
            System.out.println("An error occurred while writing to file " + filename);
            e.printStackTrace();
        }
    }


    public synchronized List<Message> readFromFile() {
        List<Message> messages = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(CSVToMessage(line));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file " + filename);
            e.printStackTrace();
        }

        return messages;
    }

    private String messageToCSV(Message message) {
        return message.getSender() + "," + message.getRoomId() + "," + message.getContent();
    }

    private Message CSVToMessage(String csv) {
        String[] parts = csv.split(",", 3);
        return new Message(parts[0], parts[1], parts[2]);
    }
}