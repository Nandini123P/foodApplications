package com.FoodApp.Model;

public class CartItem {

    private int itemId;
    private int menuID; // Added menuID field
    private int RestaurantID;
    private String name;
    private int quantity;
    private double price;

    // Default constructor
    public CartItem() {
        super();
    }

    // Constructor with restaurantID, name, quantity, and price
    public CartItem(int restaurantID, String name, int quantity, double price) {
        super();
        this.RestaurantID = restaurantID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Constructor with itemId, menuID, restaurantID, name, quantity, and price
    public CartItem(int itemId, int menuID, int restaurantID, String name, int quantity, double price) {
        super();
        this.itemId = itemId;
        this.menuID = menuID; // Assigning the menuID
        this.RestaurantID = restaurantID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter and Setter for itemId
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    // Getter and Setter for menuID
    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    // Getter and Setter for RestaurantID
    public int getRestaurantID() {
        return RestaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        RestaurantID = restaurantID;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public CartItem(int menuID, int restaurantID, String name, int quantity, double price) {
        this.menuID = menuID;
        this.RestaurantID = restaurantID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


    // Override toString method
    @Override
    public String toString() {
        return "CartItem [itemId=" + itemId + ", menuID=" + menuID + ", RestaurantID=" + RestaurantID + ", name=" + name 
                + ", quantity=" + quantity + ", price=" + price + "]";
    }
   
        private String imageURL;

        // other fields...

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        // other getters/setters...
        private String category;  // e.g., "Veg", "Non-Veg", "Sweet"
        private String tag;       // e.g., "Hot", "Mild", "Spicy"

        // Existing getters/setters...

        // Add new getters/setters:
        public String getCategory() {
            return category;
        }
        public void setCategory(String category) {
            this.category = category;
        }

        public String getTag() {
            return tag;
        }
        public void setTag(String tag) {
            this.tag = tag;
        }

}
