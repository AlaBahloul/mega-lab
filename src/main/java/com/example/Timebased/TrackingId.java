package com.example.Timebased;

import com.example.Timebased.TEEEST.query;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackingId {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/time_based";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public String getTrackingid(String username) {
        String ress = "";
        try (

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT trackingid FROM users WHERE trackingid ='" + username + "'";
            System.out.println(query);

            try (PreparedStatement st = conn.prepareStatement(query)) {
                try (ResultSet rs = st.executeQuery()) {


                    if (rs.next()) {
                        System.out.println("Login successful!");
                        System.out.println(rs.getString(1));
                       ress =  rs.getString(1);
                    } else {
                        System.out.println("Invalid username or password.");
                        ress= "null";
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(com.example.Timebased.TEEEST.query.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(query.class.getName()).log(Level.SEVERE, null, ex);
        }

return ress;

    }

    public String getusername(String username) {
        String ress = "";
        try (

                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT trackingid FROM users WHERE username ='" + username + "'";
            System.out.println(query);

            try (PreparedStatement st = conn.prepareStatement(query)) {
                try (ResultSet rs = st.executeQuery()) {


                    if (rs.next()) {
                        // Successful login
                        System.out.println("Login successful!");
                        System.out.println(rs.getString(1));
                        ress =  rs.getString(1);
                    } else {
                        // Invalid credentials
                        System.out.println("Invalid username or password.");
                        ress= "null";
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(com.example.Timebased.TEEEST.query.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ress;

    }
    boolean test=false;

    public boolean authentification(String username,String password) {
        String ress = "";

        try (

                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            System.out.println(query);

            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1,username);
                st.setString(2,password);
                try (ResultSet rs = st.executeQuery()) {


                    if (rs.next()) {

                        System.out.println("Login successful!");
                        System.out.println(rs.getString(1));
                        ress =  rs.getString(1);
                        test=true;
                    } else {
                        System.out.println("Invalid username or password.");
                        ress= "null";
                        test=false;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(com.example.Timebased.TEEEST.query.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(query.class.getName()).log(Level.SEVERE, null, ex);
        }


        return test;

    }


}
