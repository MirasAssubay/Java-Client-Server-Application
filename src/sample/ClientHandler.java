package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientHandler {
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    public static ArrayList<User> users;
    public User user;
    static {
        try {
            oos = new ObjectOutputStream(Main.socket.getOutputStream());
            ois = new ObjectInputStream(Main.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeRequest(String operationType) throws IOException {
        oos.writeObject(new Request(operationType));
    }
    public void writeRequest(String operationType, ArrayList<User> users) throws IOException {
        oos.writeObject(new Request(operationType, users));
    }
    public void writeRequest(String operationType, User user) throws IOException {
        oos.writeObject(new Request(operationType, user));
    }
    public boolean checkRequest(String operationType){
        try {
            Request request;
            request = (Request) ois.readObject();
            if(request.getOperationType().equals(operationType)){
                users = request.getUsers();
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
