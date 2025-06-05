package com.FoodApp.Impl;

import java.sql.*;
import java.util.*;

import com.Connector.ConnectorFactory;
import com.FoodApp.DAO.menuDAO;
import com.FoodApp.Model.MenuPojo;

public class menuDAOImpl implements menuDAO {

    @Override
    public List<MenuPojo> getMenu() {
        ArrayList<MenuPojo> menulist = new ArrayList<>();
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "SELECT * FROM menu";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                int menuID = res.getInt(1);
                int restaurantID = res.getInt(2);
                String name = res.getString(3);
                String description = res.getString(4);
                int price = res.getInt(5);
                int rating = res.getInt(6);
                String isAvailable = res.getString(7);
                String image = res.getString(8);

                MenuPojo menuPojo = new MenuPojo(menuID, restaurantID, name, description, price, rating, isAvailable, image);
                menulist.add(menuPojo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return menulist;
    }

    @Override
    public List<MenuPojo> getMenuByRestaurantID(int restaurantID) {
        List<MenuPojo> menuList = new ArrayList<>();
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "SELECT * FROM menu WHERE restaurantID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, restaurantID);
            ResultSet res = pstmt.executeQuery();
            
            while (res.next()) {
                int menuID = res.getInt(1);
                int restID = res.getInt(2);
                String name = res.getString(3);
                String description = res.getString(4);
                int price = res.getInt(5);
                int rating = res.getInt(6);
                String isAvailable = res.getString(7);
                String image = res.getString(8);

                MenuPojo menuPojo = new MenuPojo(menuID, restID, name, description, price, rating, isAvailable, image);
                menuList.add(menuPojo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }
    
    @Override
    public MenuPojo getMenu(int menuID) {
        MenuPojo menuPojo = null;
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "SELECT * FROM menu WHERE menuID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, menuID);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                menuPojo = new MenuPojo(
                    res.getInt(1),
                    res.getInt(2),
                    res.getString(3),
                    res.getString(4),
                    res.getInt(5),
                    res.getInt(6),
                    res.getString(7),
                    res.getString(8)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuPojo;
    }

    @Override
    public boolean updateMenu(MenuPojo m) {
        int result = 0;
        try {
            Connection con = ConnectorFactory.requestConnection();
            String query = "UPDATE menu SET name = ?, description = ?, price = ?, rating = ?, isAvailable = ?, image = ? WHERE menuID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, m.getName());
            pstmt.setString(2, m.getDescription());
            pstmt.setInt(3, m.getPrice());
            pstmt.setInt(4, m.getRating());
            pstmt.setString(5, m.getIsAvailable());
            pstmt.setString(6, m.getImage());
            pstmt.setInt(7, m.getMenuID());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0;
    }

	@Override
	public MenuPojo getMenudetails(int menuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMenu(int menuID, int RestaurantID, String name, String Description, int price, int Rating,
			String isAvailable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteMenu(int id1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MenuPojo fetchSpecific(int menuID) {
		// TODO Auto-generated method stub
		return null;
	}
}

