package com.FoodApp.Impl;

import com.FoodApp.Model.FavoritePojo;
import com.FoodApp.Model.RestaurantPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDAOImpl {
    private Connection conn;

    public FavoriteDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public boolean addFavorite(FavoritePojo fav) throws SQLException {
        String query = "INSERT INTO favorites (u_id, RestaurantID) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, fav.getUserId());
        ps.setInt(2, fav.getRestaurantId());
        int result = ps.executeUpdate();
        return result > 0;
    }

    public List<RestaurantPojo> getFavoritesByUserId(int userId) throws SQLException {
        String query = "SELECT r.* FROM restaurant r JOIN favorites f ON r.RestaurantID = f.RestaurantID WHERE f.u_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        List<RestaurantPojo> favorites = new ArrayList<>();
        while (rs.next()) {
            RestaurantPojo r = new RestaurantPojo();
            r.setRestaurantId(rs.getInt("restaurantId"));
            r.setName(rs.getString("name"));
            r.setAddress(rs.getString("address"));
            r.setCuisineType(rs.getString("cuisineType"));
            r.setRatings(rs.getInt("ratings"));
           // r.setImagePath("https://via.placeholder.com/300x180.png?text=" + rs.getString("name").replace(" ", "+"));
            favorites.add(r);
        }
        return favorites;
    }

    public boolean removeFavorite(int userId, int restaurantId) throws SQLException {
        String query = "DELETE FROM favorites WHERE u_id = ? AND RestaurantID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, userId);
        ps.setInt(2, restaurantId);
        return ps.executeUpdate() > 0;
    }
}
