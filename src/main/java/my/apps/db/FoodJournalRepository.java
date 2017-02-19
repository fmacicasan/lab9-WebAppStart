package my.apps.db;

import my.apps.domain.JournalEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author flo
 * @since 19/02/2017.
 */
public class FoodJournalRepository {


    final static String URL = "jdbc:postgresql://IP:5432/test";
    final static String USERNAME = "fasttrackit_dev";
    final static String PASSWORD = "fasttrackit_dev";

    public static void insert(JournalEntry entry) throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO foodJournal( date, time, meal, food) VALUES (?,?, ?, ?)");
        pSt.setDate(1, entry.getDate());
        pSt.setString(2, entry.getTime());
        pSt.setString(3, entry.getMeal());
        pSt.setString(4, entry.getFood());

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    public static List<JournalEntry> read() throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT id, date, time, meal, food FROM foodJournal");

        // 5. iterate the result set and print the values
        List<JournalEntry> journalEntries = new ArrayList<>();
        while (rs.next()) {
            JournalEntry journalEntry = new JournalEntry(
                    rs.getDate("date"),
                    rs.getString("time"),
                    rs.getString("meal"),
                    rs.getString("food")
            );
            journalEntry.setId(rs.getLong("id"));
            journalEntries.add(journalEntry);
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
        return journalEntries;
    }
}
