package com.FoodApp.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FoodApp.Model.RestaurantPojo;

public interface RestaurantDAO {

    // Method to get restaurant by Restaurant ID
    int getRestaurant(RestaurantPojo r);

    // Method to get all restaurants
    ArrayList<RestaurantPojo> getAllRestaurant();

    // Method to get restaurant by email (or another unique identifier)
    RestaurantPojo getRestaurant(String email);

    // Method to update restaurant details
    static boolean updateRestaurant(RestaurantPojo r) {
		// TODO Auto-generated method stub
		return false;
	}

    // Method to delete restaurant by email (or another unique identifier)
    int Restaurant(String email);

    // Method to extract restaurant data from ResultSet (used internally)
    RestaurantPojo extractRestaurantFromResultSet(ResultSet rs) throws SQLException;

	static RestaurantPojo getRestaurantById(int restaurantId) {
		// TODO Auto-generated method stub
		return null;
	}

	List<RestaurantPojo> getAllRestaurants();
}
