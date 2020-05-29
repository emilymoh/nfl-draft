package maven.nflDraft;

import java.net.URI;
import java.sql.*;
import java.util.Map;

public class App {
    public static void main(String[] argv) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("\t\t\t*********************************************");
        System.out.println("\t\t\t*********************************************");
        System.out.println("\t\t\t*********************************************");
        System.out.println("\t\t\t*********************************************");
        System.out.println("\t\t\t*********************************************");
        System.out.println("\t\t\t********WELCOME TO THE NFL DRAFT QUIZ********");      
        System.out.println("\t\t\t***Written by Emily and Aaron Mohrenweiser***");      
        System.out.println("\t\t\t*********************************************");   
        System.out.println("\t\t\t*********************************************");
        System.out.println("\t\t\t*********************************************");
        System.out.println("\t\t\t*********************************************");
        System.out.println("\t\t\t*********************************************");   
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");


        //ElephantSQL Environment Variables
        String username = "avpqaajq";
        String password = "l75pwkJS7ly1eIcwy_IX9ayydqexfhQc";
        String dbUrl = "jdbc:postgresql://hansken.db.elephantsql.com:5432/avpqaajq";

        //Declare Connection and Statement
        Connection db;
        Statement st;

        //Create the connection to the database as well as the statement, if there is an error, print message
        try {
            db = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("connection successfully made");
            st = db.createStatement();
        } catch (SQLException e) {
            System.out.println("There was an error connecting to the database");
            e.printStackTrace();
            return;
        }

        //Begin the first sql query
        ResultSet rs;
        try {
            rs = st.executeQuery("SELECT * FROM years");
            System.out.println("The possible years are: ");
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("There was an error getting the data from the test table");
            e.printStackTrace();
            return;
        }
                
    }

}

