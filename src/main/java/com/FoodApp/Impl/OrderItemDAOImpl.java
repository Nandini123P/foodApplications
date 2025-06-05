package com.FoodApp.Impl;

import com.FoodApp.DAO.OrderItemDAO;
import com.FoodApp.Model.OrderItemPojo;
import com.Connector.ConnectorFactory;

import java.sql.*;
import java.util.ArrayList;

public class OrderItemDAOImpl implements OrderItemDAO {

    // Insert new order item
    @Override
    public int insertOrderItem(OrderItemPojo item) {
        int status = 0;
        try (Connection con = ConnectorFactory.requestConnection()) {
            String query = "INSERT INTO order_items (orderId, menuId, restaurantId, name, quantity, price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getRestaurantId());
            pstmt.setString(4, item.getName());
            pstmt.setInt(5, item.getQuantity());
            pstmt.setDouble(6, item.getPrice());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Get all order items by orderId
    @Override
    public ArrayList<OrderItemPojo> getOrderItemsByOrderId(int orderId) {
        ArrayList<OrderItemPojo> orderItems = new ArrayList<>();
        try (Connection con = ConnectorFactory.requestConnection()) {
            String query = "SELECT * FROM order_items WHERE orderId = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderItemPojo item = extractOrderItemFromResultSet(rs);
                orderItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    // Delete order items by orderId
    @Override
    public int deleteOrderItemsByOrderId(int orderId) {
        int status = 0;
        try (Connection con = ConnectorFactory.requestConnection()) {
            String query = "DELETE FROM order_items WHERE orderId = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, orderId);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Extract single OrderItemPojo from ResultSet
    @Override
    public OrderItemPojo extractOrderItemFromResultSet(ResultSet rs) throws SQLException {
        OrderItemPojo item = new OrderItemPojo();
        item.setOrderItemId(rs.getInt("orderItemId"));
        item.setOrderId(rs.getInt("orderId"));
        item.setMenuId(rs.getInt("menuId"));
        item.setRestaurantId(rs.getInt("restaurantId"));
        item.setName(rs.getString("name"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getDouble("price"));
        return item;
    }
}
