package sample;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAlco {
    private Connection conn;

    public DatabaseAlco() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/authorization?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddAlcohol(Alcohol alcohol) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO alcohol(id, name, strength, cost, amount, type) VALUES(NULL, ?, ?, ?, ?, ?)");
            ps.setString(1, alcohol.getName());
            ps.setInt(2, alcohol.getStrength());
            ps.setInt(3, alcohol.getCost());
            ps.setInt(4, alcohol.getAmount());
            ps.setString(5, alcohol.getType());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Alcohol> getAlco() throws SQLException {
        ArrayList<Alcohol> Alcohols = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alcohol");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            Alcohol alcohol = new Alcohol(id,name,strength,type,amount,cost);
            Alcohols.add(alcohol);
        }
        return Alcohols;
    }
    public ArrayList<Alcohol> getVodka() throws SQLException {
        ArrayList<Alcohol> Alcohols = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alcohol WHERE type = 'VODKA'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            Alcohol alcohol = new Alcohol(id,name,strength,type,amount,cost);
            Alcohols.add(alcohol);
        }
        return Alcohols;
    }
    public ArrayList<Alcohol> searchAlco(String names) throws SQLException {
        ArrayList<Alcohol> Alcohols = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alcohol WHERE name LIKE '%" + names + "%'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            Alcohol alcohol = new Alcohol(id,name,strength,type,amount,cost);
            Alcohols.add(alcohol);
        }
        return Alcohols;
    }
    public ArrayList<Alcohol> getWine() throws SQLException {
        ArrayList<Alcohol> Alcohols = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alcohol WHERE type = 'WINE'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            Alcohol alcohol = new Alcohol(id,name,strength,type,amount,cost);
            Alcohols.add(alcohol);
        }
        return Alcohols;
    }
    public ArrayList<Alcohol> getBeer() throws SQLException {
        ArrayList<Alcohol> Alcohols = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alcohol WHERE type = 'BEER'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            Alcohol alcohol = new Alcohol(id,name,strength,type,amount,cost);
            Alcohols.add(alcohol);
        }
        return Alcohols;
    }
    public ArrayList<Alcohol> getWhiskey() throws SQLException {
        ArrayList<Alcohol> Alcohols = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alcohol WHERE type = 'WHISKEY'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            Alcohol alcohol = new Alcohol(id,name,strength,type,amount,cost);
            Alcohols.add(alcohol);
        }
        return Alcohols;
    }
    public ArrayList<Alcohol> getRum() throws SQLException {
        ArrayList<Alcohol> Alcohols = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alcohol WHERE type = 'RUM'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            Alcohol alcohol = new Alcohol(id,name,strength,type,amount,cost);
            Alcohols.add(alcohol);
        }
        return Alcohols;
    }
    public ArrayList<Alcohol> getGin() throws SQLException {
        ArrayList<Alcohol> Alcohols = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alcohol WHERE type = 'GIN'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int strength = rs.getInt("strength");
            int amount = rs.getInt("amount");
            int cost = rs.getInt("cost");
            String type = rs.getString("type");
            Alcohol alcohol = new Alcohol(id,name,strength,type,amount,cost);
            Alcohols.add(alcohol);
        }
        return Alcohols;
    }
    public void editAlco(int id, int amount, int cost){
        try{
            PreparedStatement ps=conn.prepareStatement("UPDATE alcohol SET amount=?, cost=? WHERE id=?");
            ps.setInt(1, amount);
            ps.setInt(2, cost);
            ps.setInt(3, id);
            int rows= ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateAlco(int id, int amount) {
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE alcohol SET amount=amount-? WHERE id=?");
            ps.setInt(1,amount);
            ps.setInt(2,id);
            int rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAlco(int id){
        try{
            PreparedStatement ps=conn.prepareStatement("DELETE FROM alcohol WHERE id=?");
            ps.setInt(1, id);
            int rows= ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
