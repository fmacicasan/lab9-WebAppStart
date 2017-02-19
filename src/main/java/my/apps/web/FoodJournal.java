package my.apps.web;

import my.apps.domain.JournalEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/foodJournal")
public class FoodJournal extends HttpServlet {

    private int counter;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String meal = request.getParameter("meal");
        String food = request.getParameter("food");

        System.out.println(date + time);
        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");

        try {
            Date validDate = Date.valueOf(date);
            JournalEntry entry = new JournalEntry(validDate, time, meal, food);
            insert(entry);
            out.println("<b>Inserted new meal" + meal + "</b>");
        } catch (IllegalArgumentException e) {
            out.println("<dif class='error'><b>Unable to parse date! Expected format is yyyy-MM-dd but was " + date);
        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        }


        out.println("<br/><a href='/'>Go Back</a>");

        // finished writing, send to browser
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Get count </title>");
        out.println("</head>");

        out.println("<h2>Get count</h2>");
        out.println(counter);
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called. Counter is: " + counter);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet! Counter is:" + counter);
        super.destroy();
    }

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
}
