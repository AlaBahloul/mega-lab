package com.example.SQLINJ.Service;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class Auth {


    private static final String DB_URL = "jdbc:mysql://localhost:3306/sql_injection"; // Replace with your database URL
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASS = "";

    public static boolean connection(String l, String p){

    //public static String connection(){


        String success ="mazel";
        boolean test = false;


        //String username = JOptionPane.showInputDialog("Enter your username:");
        //String password = JOptionPane.showInputDialog("Enter your password:");

        // Initialize the database connection
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT * FROM users WHERE username ='" + l + "'" + " AND password =" + "'" + p + "'";
            //String query = "SELECT * FROM users WHERE username = ? AND password = ?";

            //String query = "SELECT * FROM users";
            System.out.println(query);

            try (PreparedStatement st = conn.prepareStatement(query)) {
                //st.setString(1,"1' OR '1");
                //st.setString(2,"admin");
                //System.out.println(st);
                try (ResultSet rs = st.executeQuery()) {

                    if (rs.next()) {
                        // Successful login
                        System.out.println("Login successful!");
                        System.out.println(rs.getString(1));
                        success = "true";
                        test = true;

                    } else {
                        // Invalid credentials


                        System.out.println("Invalid username or password.");
                        success = "false";
                        test = false;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("False Statemenet ");
            }
        } catch (SQLException ex) {
            System.out.println("False connection parameter ");
        }

        System.out.println(success);
        return  test;
                //success;



    }

    public static void main(String[] args) {


        System.out.println(connection("1' OR '1","1' OR '1"));

    }
}
