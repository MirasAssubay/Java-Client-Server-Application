package sample;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/authorization?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Order addOrder(Order order) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO orders(id, name, strength, cost, amount, type, payment, address) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, order.getName());
            ps.setInt(2, order.getStrength());
            ps.setInt(3, order.getCost());
            ps.setInt(4, order.getAmount());
            ps.setString(5, order.getType());
            ps.setDouble(6,order.getPayment());
            ps.setString(7, order.getAddress());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    public ArrayList<Order> getOrder() throws SQLException {
        ArrayList<Order> Orders = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM orders");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            int payment = rs.getInt("payment");
            String address = rs.getString("address");
            Order order = new Order(id,name,strength,type,amount,cost,payment,address);
            Orders.add(order);
        }
        return Orders;
    }
    public void deleteOrder(int id){
        try{
            PreparedStatement ps=conn.prepareStatement("DELETE FROM orders WHERE id=?");
            ps.setInt(1, id);
            int rows= ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
