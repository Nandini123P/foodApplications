package com.FoodApp;

import java.util.HashMap;

public class UserDatabase {
    private static HashMap<String, String> users = new HashMap<>(); // Email and Password

    public static boolean registerUser(String name, String email, String password, String mobile) {
        if (users.containsKey(email)) {
            return false; // User already exists
        }
        users.put(email, password);
        return true;
    }

    public static boolean validateUser(String email, String password) {
        return users.containsKey(email) && users.get(email).equals(password);
    }
}

