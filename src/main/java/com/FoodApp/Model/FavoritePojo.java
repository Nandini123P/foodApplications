package com.FoodApp.Model;

public class FavoritePojo {
    private int userId;
    private int restaurantId;

    public FavoritePojo(int userId, int restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
