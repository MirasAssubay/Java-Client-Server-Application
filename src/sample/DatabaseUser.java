package sample;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseUser {
    private Connection conn;

    public DatabaseUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/authorization?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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