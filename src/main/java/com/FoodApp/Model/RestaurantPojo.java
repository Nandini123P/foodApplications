package com.FoodApp.Model;

import java.sql.Timestamp;

public class RestaurantPojo {
    private int restaurantId;
    private String name;
    private String cuisineType;
    private String address;
    private int ratings;
    private Timestamp deliveryTime;
    private String isActive;
    private String image; // Base64 encoded image or relative path to image
    
    // Getters and Setters
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // New method to get the relative image path (use it in JSP)
    public String getImagePath() {
        // If image is Base64, return it directly, otherwise return relative path
        if (image != null && image.startsWith("data:image")) {
            return image;  // Base64 encoded image
        } else {
            return "images/" + image;  // Assuming 'image' holds the relative file name
        }
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "RestaurantPojo [restaurantId=" + restaurantId + ", name=" + name + ", cuisineType=" + cuisineType
                + ", address=" + address + ", ratings=" + ratings + ", deliveryTime=" + deliveryTime + ", isActive="
                + isActive + ", image=" + image + "]";
    }

    // Constructors
    public RestaurantPojo(int restaurantId, String name, String cuisineType, String address, int ratings,
                          Timestamp deliveryTime2, String isActive, String image) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisineType = cuisineType;
        this.address = address;
        this.ratings = ratings;
        this.deliveryTime = deliveryTime2;
        this.isActive = isActive;
        this.image = image;
    }

    public RestaurantPojo(String name, String cuisineType, String address, int ratings, Timestamp deliveryTime,
                          String isActive, String image) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.address = address;
        this.ratings = ratings;
        this.deliveryTime = deliveryTime;
        this.isActive = isActive;
        this.image = image;
    }

    public RestaurantPojo() {
    }
}

