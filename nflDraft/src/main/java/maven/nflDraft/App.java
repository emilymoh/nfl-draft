package maven.nflDraft;

import java.net.URI;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.ThreadLocalRandom;

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
            st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            System.out.println("There was an error connecting to the database");
            e.printStackTrace();
            input.close();
            return;
        }

        // ask user for name
        String userName = null;
        boolean inputUsername = true;
        while (inputUsername) {
            System.out.println("Please enter the name you want to be used for the leaderboard: ");
            userName = stringUserInput(input);
            System.out.println("The name you entered is: " + userName);
            System.out.println("Whould you like to enter a different name? (Yes/No)");
            String newName = stringUserInput(input);
            if (newName.equals("no")) {
                inputUsername = false;
            }
        }

        // ask user for league
        String leagueName = null;
        boolean notValidLeagueName = true;
        while (notValidLeagueName) {
            System.out.println("Please choose a league");
            System.out.println("Option 1: NFL   Option 2: NBA");
            leagueName = stringUserInput(input);
            if (leagueName.equals("nfl")) {
                System.out.println("you chose NFL");
                notValidLeagueName = false;
            } else if (leagueName.equals("nba")) {
                System.out.println("you chose NBA");
                notValidLeagueName = false;
            } else {
                System.out.println("you did not enter a valid option");
                System.out.println("pls try again");
            }

        }

        /*
        // ask question type
        String questionType = null;
        boolean notValidQuestionType = true;
        while (notValidQuestionType) {
            System.out.println("Choose a question type");
            System.out.println("Option 1: draft order     Option 2: combine         Option 3: teams");
            questionType = stringUserInput(input);
            if (questionType.equals("draft order")) {
                System.out.println("you chose draft order");
                notValidQuestionType = false;
            } else if (questionType.equals("combine")) {
                System.out.println("you chose combine");
                notValidQuestionType = false;
            } else if (questionType.equals("teams")) {
                System.out.println("you chose teams");
                notValidQuestionType = false;
            } else {
                System.out.println(questionType);
                System.out.println("you did not enter a valid option");
                System.out.println("pls try again");
            }
        }
        */

        ResultSet rs;

        System.out.println("Please choose which year of the "+ leagueName +" draft you would like to be quizzed on: ");
        System.out.println("If you would like to be quizzed on all of the years, please type '0'");

        rs = executeStatement(st, "SELECT year FROM years");
        printResults(rs);

        int year = integerUserInput(input);

        System.out.println("The quiz will now begin...");

        int score = 0;

        ResultSet questions = null;

        Scanner answerScanner = new Scanner(System.in);

        if (year == 0) {
            questions = executeStatement(st, "SELECT * FROM questions WHERE league like '" + leagueName + "'");
            try {
                if (!questions.next()) {
                    System.out.println("No questions found");
                } else {
                    boolean one_question = true;
                    while(one_question){
                        do {
                                System.out.print("\t" + questions.getString("question") + "\n");
                                String userAnswer = stringUserInput(answerScanner);
                                String rightAnswer = questions.getString("answer").toLowerCase();

                                while(!(userAnswer.equals(rightAnswer))){
                                    System.out.println("Try again or skip?");
                                    String userChoice = stringUserInput(answerScanner);
                                    if((Character.compare(userChoice.charAt(0), 't')) == 0){
                                        System.out.print("\t" + questions.getString("question") + "\n");
                                        userAnswer = stringUserInput(answerScanner);
                                    } 
                                    else if((Character.compare(userChoice.charAt(0), 's')) == 0){
                                        userAnswer = rightAnswer;
                                        score += -1; 
                                    }
                                }
                                score += 1;

                        } while (questions.next());
                    }
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                System.out.println("the quiz is done");

            }

        } else {
            questions = executeStatement(st, "select * from question where year = " + year + " and league like '" + leagueName + "'");
            try {
                if (!questions.next()) {
                    System.out.println("No questions found");
                } else {
                    boolean one_question = true;
                    while(one_question){
                        do {
                                System.out.print("\t" + questions.getString("question") + "\n");
                                String userAnswer = stringUserInput(answerScanner);
                                String rightAnswer = questions.getString("answer").toLowerCase();

                                while(!(userAnswer.equals(rightAnswer))){
                                    System.out.println("Try again or skip?");
                                    String userChoice = stringUserInput(answerScanner);
                                    if((Character.compare(userChoice.charAt(0), 't')) == 0){
                                        System.out.print("\t" + questions.getString("question") + "\n");
                                        userAnswer = stringUserInput(answerScanner);
                                    } 
                                    else if((Character.compare(userChoice.charAt(0), 's')) == 0){
                                        userAnswer = rightAnswer;
                                        score += -1;
                                    }
                                }
                                score += 1;
                        } while (questions.next());
                    }
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                System.out.println("the quiz is done");

            }   
        }
        // System.out.println("Your score is " + score );
        // executeUpdate(st, "insert into users value (4, '"+ userName + "', " + score ")");
        String table = "users_" + leagueName;
        int user_id =  newUserID(st, table);

        try {       
            PreparedStatement ps = db.prepareStatement("insert into " + table + " values (?,?,?)");
            
            ps.setInt(1, user_id);
            ps.setString(2, userName);
            ps.setInt(3, score);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }   

        ResultSet users = executeStatement(st, "SELECT name, score FROM " + table + " ORDER BY score desc");
        System.out.println("\t" + leagueName.toUpperCase() + " LEADERBOARD");
        System.out.println("");
        printResults(users);

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
        return returnInput.toLowerCase();
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

    public static int newUserID(Statement st, String table){
        ResultSet users = executeStatement(st, "SELECT * FROM " + table);
        //number of rows
        int numRows = 0;
        try {
            while (users.next()) {
                numRows++;
            }

        } catch (SQLException e) {
            System.out.println("There was an error getting the number of rows");
            e.printStackTrace();
        }
        //random number generator in the range of the number of rows 
        return numRows + 1;
    } 

    public static void printQuestion(ResultSet questions, Scanner input){
        boolean keepGoing = true;
        try {
            if (!questions.next()) {
                System.out.println("No records found");
            } else {
                while(keepGoing){
                    do {
                        System.out.println("\t" + questions.getString(2));
                        System.out.println("");
                        String correctAnswer = questions.getString(3);
                        String userAnswer = input.nextLine();
                        //String userAnswer = stringUserInput(input);
                        System.out.println("The correct answer is: " + correctAnswer);
                        System.out.println("The user's answer is: " + userAnswer);

                        if(!userAnswer.equals(correctAnswer)){
                            System.out.println("Try again or skip?");
                            String userChoice = stringUserInput(input);
                            if((Character.compare(userChoice.charAt(0), 't')) == 0){
                                userAnswer = stringUserInput(input);
                            } 
                            else if((Character.compare(userChoice.charAt(0), 's')) == 0){
                                //What should happen when a question is skipped?
                            }
                        } else {
                            System.out.println("Correct");
                        }
    
                    } while (questions.next());
                    
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an error printing the results");
            e.printStackTrace();
        }
    }

}

