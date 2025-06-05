package com.tap.foodAppServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.foodAppServlet.pojo.user;

public class userDAOImpl implements userDAO {
    private final String INSERT_QUERY = "INSERT INTO `user`(`u_name`,`email`,`mobile`,`password`,`address`) VALUES(?,?,?,?,?)";
    private final String SELECT_ALL = "SELECT * FROM `user`"; // Corrected query for all users
    private final String SELECT_ON_EMAIL = "SELECT * FROM `user` WHERE email=?";
    
    private String url = "jdbc:mysql://localhost:3306/oct_jdbc";
    private String username = "root";
    private String password = "root";
    private Connection con;

    public userDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insert(user u) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {
            pstmt.setString(1, u.getU_name());
            pstmt.setString(2, u.getEmail());
            pstmt.setInt(3, u.getMobile());
            pstmt.setString(4, u.getPassword());
            pstmt.setString(5, u.getAddress());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // or handle the error as necessary
        }
    }

    @Override
    public int addUser(user u) {
        return insert(u); // Assuming this method should add a user
    }

    @Override
    public ArrayList<user> getAllUsers() {
        ArrayList<user> userList = new ArrayList<>();
        try (Statement stmt = con.createStatement(); 
             ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                user u = new user(
                    resultSet.getString("u_name"),
                    resultSet.getString("email"),
                    resultSet.getInt("mobile"),
                    resultSet.getString("password"),
                    resultSet.getString("address")
                );
                userList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public user getUser(String email) {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_ON_EMAIL)) {
            pstmt.setString(1, email);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new user(
                    resultSet.getString("u_name"),
                    resultSet.getString("email"),
                    resultSet.getInt("mobile"),
                    resultSet.getString("password"),
                    resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no user found
    }

    @Override
    public int updateUser(user u) {
        // TODO: Implement this method if necessary
        return 0;
    }

    @Override
    public int deleteUser(String email) {
        // TODO: Implement this method if necessary
        return 0;
    }
}
