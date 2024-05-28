package com.example.IDOR;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/time_based"; // Replace with your database URL
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASS = ""; // Replace with your database password


    boolean test=false;

    public boolean authentification(String username,String password) {
        String ress = "";

        try (

                java.sql.Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            //String query = "SELECT * FROM users WHERE username ='" + username + "'" + " AND password =" + "'" + password + "'";
            String query = "SELECT * FROM idoruser WHERE username = ? AND password = ?";
            System.out.println(query);

            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1,username);
                st.setString(2,password);
                System.out.println(query);
                try (ResultSet rs = st.executeQuery()) {


                    if (rs.next()) {

                        System.out.println("Login successful!");
                        System.out.println(rs.getString(1));

                        test=true;
                    } else {

                        System.out.println("Invalid username or password.");

                        test=false;
                    }
                }
            } catch (SQLException ex) {

            }
        } catch (SQLException ex) {

        }

        //return ress;
        return test;

    }


}
