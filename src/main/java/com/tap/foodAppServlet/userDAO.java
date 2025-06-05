package com.tap.foodAppServlet;

import java.util.ArrayList;

import com.tap.foodAppServlet.pojo.user;

public interface userDAO {
	
	int addUser(user u);
	ArrayList<user> getAllUsers();
	user getUser(String email);
	int updateUser(user u);
	int deleteUser(String email);

}
