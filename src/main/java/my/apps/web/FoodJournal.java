package my.apps.web;

import my.apps.db.FoodJournalRepository;
import my.apps.domain.JournalEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/foodJournal")
public class FoodJournal extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        //get input as string
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String meal = request.getParameter("meal");
        String food = request.getParameter("food");

        System.out.println(date + time);
        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        addStyle(out);

        try {
            Date validDate = Date.valueOf(date);
            JournalEntry entry = new JournalEntry(validDate, time, meal, food);
            FoodJournalRepository.insert(entry);
            out.println("<b>Inserted new journal entry" + entry + "</b>");
        } catch (IllegalArgumentException e) {
            out.println("<dif class='error'><b>Unable to parse date! Expected format is yyyy-MM-dd but was " + date);
        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        }


        addBackButton(out);

        // finished writing, send to browser
        out.close();

    }

    private void addBackButton(PrintWriter out) {
        out.println("<br/><a href='/'>Go Back</a>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> My Food Journal </title>");
        addStyle(out);
        out.println("</head>");

        try {
            out.println("<h2>The Food Journal</h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Date</th>");
            out.println("<th>Time</th>");
            out.println("<th>Meal</th>");
            out.println("<th>Food</th>");
            out.println("</tr>");
            List<JournalEntry> journalEntries = FoodJournalRepository.read();
            for (JournalEntry journalEntry : journalEntries) {
                out.println("<tr>");
                out.println("<td>"+journalEntry.getId()+"</td>");
                out.println("<td>"+journalEntry.getDate()+"</td>");
                out.println("<td>"+journalEntry.getTime()+"</td>");
                out.println("<td>"+journalEntry.getMeal()+"</td>");
                out.println("<td>"+journalEntry.getFood()+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        }
        addBackButton(out);
        out.close();
    }

    private void addStyle(PrintWriter out) {
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called.");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet!");
        super.destroy();
    }
}
