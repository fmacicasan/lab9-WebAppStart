package my.apps.db;
import java.sql.*;


/**
 * Created by condor on 26/02/15.
 * FastTrackIT, 2015
 * <p/>
 * DEMO ONLY PURPOSES, IT MIGHT CONTAINS INTENTIONALLY ERRORS OR ESPECIALLY BAD PRACTICES
 *
 * make sure you refactor it and remove lots of bad practices like loading the driver multiple times or
 * repeating the same common code multiple times
 *
 * BTW, exercise 1: how we reorg this/refactor in small pieces
 */
public class DemoCRUDOperations {

    // 1. define connection params to db
    final static String URL = "jdbc:postgresql://IP:5432/test";
    final static String USERNAME = "fasttrackit_dev";
    final static String PASSWORD = "fasttrackit_dev";

    public static void main(String[] args) {
        System.out.println("Hello database users! We are going to call DB from Java");
        try {
            //demo CRUD operations
            demoCreate();
            demoRead();
            demoUpdate();
            demoDelete();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void demoCreate() throws SQLException, ClassNotFoundException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO socialuser (username, PASSWORD) VALUES (?,?)");
        pSt.setString(1, "george");
        pSt.setString(2, "password1");

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    private static void demoRead() throws ClassNotFoundException, SQLException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT username,password FROM socialuser");

        // 5. iterate the result set and print the values
        while (rs.next()) {
            System.out.print(rs.getString("username").trim());
            System.out.print("---");
            System.out.println(rs.getString("password").trim());
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
    }

    private static void demoUpdate() throws ClassNotFoundException, SQLException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("UPDATE socialuser SET username=?, PASSWORD=? WHERE username=?"); //so we have 3 params
        pSt.setString(1, "marcel");
        pSt.setString(2, "password1");
        pSt.setString(3, "george");

        // 4. execute a prepared statement
        int rowsUpdated = pSt.executeUpdate();
        System.out.println("Updated " + rowsUpdated + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }


    private static void demoDelete() throws ClassNotFoundException, SQLException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM socialuser WHERE username=?");
        pSt.setString(1, "marcel");

        // 4. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");
        // 5. close the objects
        pSt.close();
        conn.close();
    }
}