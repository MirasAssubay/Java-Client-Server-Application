package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private String operationType;
    private ArrayList<User> users;
    private User user;
    public Request(String operationType) {
        this.operationType = operationType;
    }

    public User getUser() {
        return user;
    }

    public Request(String operationType, User user) {
        this.operationType = operationType;
        this.user = user;
    }

    public Request(String operationType, ArrayList<User> users) {
        this.operationType = operationType;
        this.users = users;
    }

    public String getOperationType() {
        return operationType;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Request() {
    }
}
