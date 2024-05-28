package com.example.Timebased.TEEEST;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class query {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sql_injection"; // Replace with your database URL
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASS = ""; // Replace with your database password

    public static void main(String[] args) {

        String username = JOptionPane.showInputDialog("Enter your username:");
        String password = JOptionPane.showInputDialog("Enter your password:");

        // Initialize the database connection
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT * FROM users WHERE username ='" + username + "'" + " AND password =" + "'" + password + "'";
            System.out.println(query);

            try (PreparedStatement st = conn.prepareStatement(query)) {
                try (ResultSet rs = st.executeQuery()) {

                    if (rs.next()) {
                        // Successful login
                        System.out.println("Login successful!");
                        System.out.println(rs.getString(1));
                    } else {
                        // Invalid credentials
                        System.out.println("Invalid username or password.");

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(query.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(query.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
