import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

public class ChatClientCLI {
    private Scanner scanner = new Scanner(System.in);

    private String userName;

    private IRoomManager roomManager;

    private IMessageStore messageStore;

    private IRoom currentRoom;

    private IUserSession userSession;

    public ChatClientCLI(IRoomManager roomManager, IMessageStore messageStore) {
        this.roomManager = roomManager;
        this.messageStore = messageStore;
    }

    public void enterUserName() {
        System.out.println("Please enter your name:");
        userName = scanner.nextLine();
        System.out.println("Welcome " + userName + "!");
    }


    public int displayMenuAndGetSelection() {
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("Make a selection:");
            System.out.println("1 - See all chats");
            System.out.println("2 - Join chat");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // this discards the input and waits for next
            }
        }
        scanner.nextLine(); // consumes the leftover newline
        return choice;
    }

    public void seeAllChats() throws RemoteException {
        System.out.println("Showing all chats...");

        List<IRoom> list = roomManager.getAllRooms();
        for (IRoom r : list) {
            System.out.println(r.getRoomId() + " - " + r.getUserCount() + " users");
        }
    }

    public void sendMessage() throws RemoteException {
        System.out.println("Enter your message:");
        String message = scanner.nextLine();

        System.out.println("Sending '" + message + "'...");

        if (currentRoom != null) {
            currentRoom.sendMessageFrom(userSession, message);
        } else {
            System.out.println("You are not in a chat room, please join a chat first.");
        }
    }

    public void joinChat() throws RemoteException {
        System.out.println("Enter id/name of the chat you want to join:");
        String chatId = scanner.next();
        System.out.println("Joining chat " + chatId + "...");

        currentRoom = roomManager.findRoom(chatId);
        userSession = new UserSession(userName, currentRoom, messageStore);
        IUserSession userSessionStub = (IUserSession) UnicastRemoteObject.exportObject(userSession, 0);

        roomManager.joinRoom(currentRoom, userSessionStub);
    }

    public void exitRoom() throws RemoteException {
        System.out.println("Exiting the room...");
        userSession.leave();
        currentRoom = null;
        userSession = null;
    }

    public int displayUserMenuAndGetSelection() {
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("Make a selection:");
            System.out.println("1 - Send message");
            System.out.println("2 - Exit room");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // this discards the input and waits for next
            }
        }
        scanner.nextLine(); // consumes the leftover newline
        return choice;
    }


    public void runRoomMenu() throws RemoteException {
        while (currentRoom != null) {
            switch (displayUserMenuAndGetSelection()) {
                case 1 -> sendMessage();
                case 2 -> {
                    System.out.println("Exiting the room...");
                    exitRoom();
                }
                default -> System.out.println("Invalid selection, please try again.");
            }
        }
    }

    public void run() throws RemoteException {
        enterUserName();
        while (true) {
            switch (displayMenuAndGetSelection()) {
                case 1 -> seeAllChats();
                case 2 -> {
                    joinChat();
                    if (currentRoom != null) {
                        runRoomMenu();
                    }
                }
                default -> System.out.println("Invalid selection, please try again.");
            }
        }
    }

}
