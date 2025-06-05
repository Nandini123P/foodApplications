package com.FoodApp.DAO;

import com.FoodApp.Model.OrderItemPojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderItemDAO {
    int insertOrderItem(OrderItemPojo item);
    ArrayList<OrderItemPojo> getOrderItemsByOrderId(int orderId);
    int deleteOrderItemsByOrderId(int orderId);
    OrderItemPojo extractOrderItemFromResultSet(ResultSet rs) throws SQLException;
}
