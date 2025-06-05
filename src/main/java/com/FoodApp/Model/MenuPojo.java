package com.FoodApp.Model;

public class MenuPojo {


		private int menuID;
		private int RestaurantID;
		private String name;
		private String Description;
		private int price;
		private int Rating;
		private  String isAvailable;
		private String Image;
		
		
		public MenuPojo() {
			super();
		}
		public MenuPojo(int menuID, int restaurantID, String name, String description, int price, int rating,
				String isAvailable) {
			super();
			this.menuID = menuID;
			RestaurantID = restaurantID;
			this.name = name;
			Description = description;
			this.price = price;
			Rating = rating;
			this.isAvailable = isAvailable;
		}
		public MenuPojo(int restaurantID, String name, String description, int price, int rating, String isAvailable,
				String image) {
			super();
			RestaurantID = restaurantID;
			this.name = name;
			Description = description;
			this.price = price;
			Rating = rating;
			this.isAvailable = isAvailable;
			Image = image;
		}
		public MenuPojo(int menuID, int restaurantID, String name, String description, int price, int rating,
				String isAvailable, String image) {
			super();
			this.menuID = menuID;
			RestaurantID = restaurantID;
			this.name = name;
			Description = description;
			this.price = price;
			Rating = rating;
			this.isAvailable = isAvailable;
			Image = image;
		}
		public int getMenuID() {
			return menuID;
		}
		public int getRestaurantID() {
			return RestaurantID;
		}
		public void setRestaurantID(int restaurantID) {
			RestaurantID = restaurantID;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getRating() {
			return Rating;
		}
		public void setRating(int rating) {
			Rating = rating;
		}
		public String getIsAvailable() {
			return isAvailable;
		}
		public void setIsAvailable(String isAvailable) {
			this.isAvailable = isAvailable;
		}
		public String getImage() {
			return Image;
		}
		public void setImage(String image) {
			Image = image;
		}
		public void setMenuID(int menuID) {
			this.menuID = menuID;
		}
		@Override
		public String toString() {
			return "MenuPojo [menuID=" + menuID + ", RestaurantID=" + RestaurantID + ", name=" + name + ", Description="
					+ Description + ", price=" + price + ", Rating=" + Rating + ", isAvailable=" + isAvailable
					+ ", Image=" + Image + "]";
		}

}
