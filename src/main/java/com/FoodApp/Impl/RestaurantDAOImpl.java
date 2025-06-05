package com.FoodApp.Impl;

import com.FoodApp.DAO.RestaurantDAO;
import com.FoodApp.Model.RestaurantPojo;
import com.Connector.ConnectorFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantDAO {

    @Override
    public int getRestaurant(RestaurantPojo r) {
        int status = 0;
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "SELECT * FROM restaurant WHERE RestaurantId = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, r.getRestaurantId());
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                r.setName(res.getString("Name"));
                r.setCuisineType(res.getString("cuisineType"));
                r.setAddress(res.getString("address"));
                r.setRatings(res.getInt("ratings"));
                r.setDeliveryTime(res.getTimestamp("deliveryTime"));
                r.setIsActive(res.getString("isActive"));
                r.setImage(res.getString("Image"));
                status = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // ✅ RENAMED to match usage in JSP
    @Override
    public List<RestaurantPojo> getAllRestaurants() {
        List<RestaurantPojo> restaurantList = new ArrayList<>();
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "SELECT * FROM restaurant";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                RestaurantPojo r = new RestaurantPojo();
                r.setRestaurantId(res.getInt("RestaurantId"));
                r.setName(res.getString("Name"));
                r.setCuisineType(res.getString("cuisineType"));
                r.setAddress(res.getString("address"));
                r.setRatings(res.getInt("ratings"));
                r.setDeliveryTime(res.getTimestamp("deliveryTime"));
                r.setIsActive(res.getString("isActive"));
                r.setImage(res.getString("Image"));
                restaurantList.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public RestaurantPojo getRestaurant(String email) {
        RestaurantPojo r = null;
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "SELECT * FROM restaurant WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                r = new RestaurantPojo();
                r.setRestaurantId(res.getInt("RestaurantId"));
                r.setName(res.getString("Name"));
                r.setCuisineType(res.getString("cuisineType"));
                r.setAddress(res.getString("address"));
                r.setRatings(res.getInt("ratings"));
                r.setDeliveryTime(res.getTimestamp("deliveryTime"));
                r.setIsActive(res.getString("isActive"));
                r.setImage(res.getString("Image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public int Restaurant(String email) {
        int status = 0;
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "DELETE FROM restaurant WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public RestaurantPojo extractRestaurantFromResultSet(ResultSet rs) throws SQLException {
        RestaurantPojo r = new RestaurantPojo();
        r.setRestaurantId(rs.getInt("RestaurantId"));
        r.setName(rs.getString("Name"));
        r.setCuisineType(rs.getString("cuisineType"));
        r.setAddress(rs.getString("address"));
        r.setRatings(rs.getInt("ratings"));
        r.setDeliveryTime(rs.getTimestamp("deliveryTime"));
        r.setIsActive(rs.getString("isActive"));
        r.setImage(rs.getString("Image"));
        return r;
    }

	@Override
	public ArrayList<RestaurantPojo> getAllRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}
}
