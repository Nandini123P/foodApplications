package com.FoodApp.Model;

public class Order {
    private int orderId;
    private int u_id;
    private int restaurantId;
    private double totalAmount;
    private String status;
    private String paymentMode;
    private String deliveryAddress;

    public Order(int orderId, int userId, int restaurantId, double totalAmount, String status, String paymentMode, String deliveryAddress) {
        this.orderId = orderId;
        this.u_id = u_id;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
        this.deliveryAddress = deliveryAddress;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return u_id; }
    public void setUserId(int userId) { this.u_id = userId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
}
