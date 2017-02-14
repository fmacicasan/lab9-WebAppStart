package my.apps.web;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/articlesArchive")
public class ArticlesArchive extends HttpServlet {

    private int counter;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String link = request.getParameter("link");
        String date = request.getParameter("date");
        String summary = request.getParameter("summary");
        String domain = request.getParameter("domain");
        Article article = new Article(link, date, summary, domain);

        System.out.println(link + date);
        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Are you sure? </h3>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
        out.println("<b>" + article.toString() +  "</b><br />");
        out.println("<a href='/'>Go Back</a>");

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
}