package maven.nflDraft;

import java.net.URI;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Scanner input = new Scanner(System.in);

        // ElephantSQL Environment Variables
        String username = "avpqaajq";
        String password = "l75pwkJS7ly1eIcwy_IX9ayydqexfhQc";
        String dbUrl = "jdbc:postgresql://hansken.db.elephantsql.com:5432/avpqaajq";

        // Declare Connection and Statement
        Connection db;
        Statement st;

        // Create the connection to the database as well as the statement, if there is
        // an error, print message
        try {
            db = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("connection successfully made");
            st = db.createStatement();
        } catch (SQLException e) {
            System.out.println("There was an error connecting to the database");
            e.printStackTrace();
            return;
        }

        // ResultSet rs;
        // System.out.println("Testing helper methods");
        // rs = executeStatement(st, "Select * from users");
        // printResults(rs);

        // ask user for name
        String userName = null;

        System.out.println("Enter Name");
        userName = input.next();
        System.out.println("The name you entered is: " + userName);

        // ask user for league
        String lName = null;
        String leagueName = null;

        boolean notValidLeagueName = true;
        while (notValidLeagueName) {
            System.out.println("choose a league");
            System.out.println("Option 1: NFL   Option 2: NBA");
            lName = input.next();
            leagueName = lName.toLowerCase();

            if (leagueName.equals("nfl")) {
                System.out.println("you chose NFL");
                notValidLeagueName = false;
            } else if (leagueName.equals("nba")) {
                System.out.println("you chose NBA");
                notValidLeagueName = false;
            } else {
                System.out.println(leagueName);
                System.out.println("you did not enter a valid option");
                System.out.println("pls try again");
            }

        }

        // ask question type
        String lquestionType = null;
        String questionType = null;

        boolean notValidQuestionType = true;
        while (notValidQuestionType) {
            System.out.println("Choose a question type");
            System.out.println("Option 1: draft order     Option 2: combine         Option 3: teams");
            questionType = input.nextLine();
            lquestionType = questionType.toLowerCase();
            if (lquestionType.equals("draft order")) {
                System.out.println("you chose draft order");
                notValidQuestionType = false;
            } else if (lquestionType.equals("combine")) {
                System.out.println("you chose combine");
                notValidQuestionType = false;
            } else if (lquestionType.equals("teams")) {
                System.out.println("you chose teams");
                notValidQuestionType = false;
            } else {
                System.out.println(questionType);
                System.out.println("you did not enter a valid option");
                System.out.println("pls try again");
            }
        }

        // Begin the first sql query
        ResultSet rs;
        try {
            rs = st.executeQuery("SELECT * FROM years");
            System.out.println("The possible years are: ");
            while (rs.next()) {
                System.out.println("\t" + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("There was an error getting the data from the test table");
            e.printStackTrace();
            return;
        }

        System.out.println("choose optional year");
        System.out.println("not valid year");

    }

    // HELPER METHODS (do not touch)

    // Call this method when you want the user to input an integer
    // Behavior: Will continue prompting the user to input an integer until a valid
    // one is inputed
    // returns the integer that the user inputs
    public static int integerUserInput(Scanner input) {
        int returnInput = -1;
        while (returnInput == -1) {
            if (input.hasNextInt()) {
                returnInput = input.nextInt();
                return returnInput;
            } else {
                input.nextLine();
                System.out.println("Please enter valid integer");
            }
        }
        return returnInput;
    }

    // Call this method when you want the user to input a string
    // Behavior: Will continue prompting the user to input a string until a
    // characters are inputed (will accept numbers along with characters)
    // returns the string that the user inputs
    public static String stringUserInput(Scanner input) {
        int loop = -1;
        String returnInput = "Please enter valid string";
        while (loop == -1) {
            if (input.hasNext()) {
                returnInput = input.nextLine();
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(returnInput);
                if (m.matches()) {
                    System.out.println("Please enter valid string");
                } else
                    loop = 0;
            } else {
                input.nextLine();
                System.out.println("Please enter valid string");
            }
        }
        return returnInput;
    }

    //Call this method when you want to execute a query, it will automatically catch exceptions
    //Behavior: If any exceptions, error statement 
    //Return: ResultSet
    public static ResultSet executeStatement(Statement st, String query) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.println("There was an error executing the query");
            e.printStackTrace();
        }

        return rs;
    }

    //Call this method when you want to print a ResultSet
    //Behavior: This method will automatically count the number of columns in the set and print out every record
    //Return: Does not return anything, prints directly to the console 
    public static void printResults(ResultSet rs) {
        ResultSetMetaData rsmd;
        int num_columns = 0;
        try {
            rsmd = rs.getMetaData();
            num_columns = rsmd.getColumnCount();
        } catch (SQLException e1) {
            System.out.println("There was an error getting the number of columns from the ResultSet");
            e1.printStackTrace();
        }

        try {
            if (!rs.next()) {
                System.out.println("No records found");
            } else {
                do {
                    for (int i = 1; i <= num_columns; i++){
                        System.out.print("\t" + rs.getString(i));
                    }
                    System.out.println("");
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("There was an error printing the results");
            e.printStackTrace();
        }

    }

}

