package com.FoodApp.Impl;

import java.util.HashMap;
import java.util.Map;

import com.FoodApp.Model.CartItem;

public class CartDAOImpl {

	private Map<Integer, CartItem> items;
	
	public CartDAOImpl() {
		this.items = new HashMap<>();
	}
	
	public Map<Integer, CartItem> addItem(Map<Integer, CartItem> cart, CartItem item) {
        int menuID = item.getMenuID();
        
        // If item already exists, update quantity
        if (cart.containsKey(menuID)) {
            CartItem existingItem = cart.get(menuID);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            // Add new item to the cart
            cart.put(menuID, item);
        }

        return cart;
    }
	
	public void updateItem(int itemId, int quantity) {
		
		if(items.containsKey(itemId)) {
			if(quantity <= 0) {
				items.remove(itemId);
			}
			else {
				items.get(itemId).setQuantity(quantity);
			}
		}
	}
	
	public void removeItem(int itemId) {
		
		items.remove(itemId);
	}
	
	public Map<Integer, CartItem> getItems(){
		
		return items;
	}
	
	public void clear() {
		
		items.clear();
	}
}
