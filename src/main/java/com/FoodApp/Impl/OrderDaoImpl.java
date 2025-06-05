package com.FoodApp.Impl;

import com.FoodApp.DAO.OrderDao;
import com.FoodApp.Model.Order;
import com.Connector.ConnectorFactory;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {

    // Insert new order and return generated order ID
    @Override
    public int insert(Order order) {
        int orderId = -1;

        try (Connection con = ConnectorFactory.requestConnection()) {
            String query = "INSERT INTO orders (u_id, RestaurantID, total_amount, status, payment_mode, delivery_address) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setDouble(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentMode());
            pstmt.setString(6, order.getDeliveryAddress());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }

    // Get order by ID
    public Order getOrderById(int orderId) {
        Order order = null;
        try (Connection con = ConnectorFactory.requestConnection()) {
            String query = "SELECT * FROM orders WHERE order_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                order = new Order(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getInt("restaurant_id"),
                    rs.getDouble("total_amount"),
                    rs.getString("status"),
                    rs.getString("payment_mode"),
                    rs.getString("delivery_address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }
}
