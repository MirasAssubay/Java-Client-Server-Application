package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Connection conn;
    private Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    private Request request = new Request();
    private DatabaseUser db = new DatabaseUser();

    public ServerThread(Socket socket, Connection connection) {
        this.socket = socket;
        this.conn = connection;
        try {
            inputStream = new ObjectInputStream(this.socket.getInputStream());
            outputStream = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while ((request = (Request) inputStream.readObject()) != null){
                if (request.getOperationType().equals("GET_USERS")){
                    outputStream.writeObject(new Request("USERS_GOT", db.getUsers()));
                }
                if (request.getOperationType().equals("ADD_USER")){
                    db.addUser(request.getUser());
                    outputStream.writeObject(new Request("SUCCESSFULLY"));
                }
            }
        }
        catch (IOException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
    public boolean addUser(User user){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user(id, username,password) VALUES(NULL, ?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM user");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            User user = new User(id,username,password);
            users.add(user);
        }

        ps = conn.prepareStatement("SELECT role FROM roles INNER JOIN user ON user.id=roles.id_user");
        ps = conn.prepareStatement("SELECT * FROM roles");
        rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id_user");
            for (User user : users) {
                if (id == user.getId()){
                    user.setRole(rs.getString("role"));
                }
            }
        }
        return users;
    }
}
