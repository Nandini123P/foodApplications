package com.FoodApp.DAO;

import java.util.List;

import com.FoodApp.Model.MenuPojo;


public interface menuDAO {

List getMenu();
	
	MenuPojo getMenudetails(int menuid);
	
	void insertMenu(int menuID, int RestaurantID, String name, String Description, int price, int Rating,String isAvailable);
	
	boolean updateMenu(MenuPojo m);
	
	boolean deleteMenu(int id1);
	
	MenuPojo fetchSpecific(int menuID);

	MenuPojo getMenu(int menuID);

	List<MenuPojo> getMenuByRestaurantID(int restaurantID);
}
