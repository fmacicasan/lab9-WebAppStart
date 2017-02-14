package my.apps.web;
import my.apps.db.ArticleRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/articlesArchive")
public class ArticlesArchive extends HttpServlet {

    private int counter;

    private ArticleRepository articleRepository = new ArticleRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String link = request.getParameter("link");
        String date = request.getParameter("date");
        String summary = request.getParameter("summary");
        String domain = request.getParameter("domain");

        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        addStyle(out);
        try {
            Date validDate = java.sql.Date.valueOf(date);
            Article article = new Article(link, validDate, summary, domain);
            out.println("<h3>New article...</h3>");
            articleRepository.insert(article);
            out.println("<b>" + article.toString() +  "</b><br />");
        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        } catch (IllegalArgumentException e) {
            out.println("<dif class='error'><b>Unable to parse date! Expected format is yyyy-MM-dd but was " + date);
        }

        addGoBack(out);

        // finished writing, send to browser
        out.close();

    }

    private void addGoBack(PrintWriter out) {
        out.println("<a href='/'>Go Back</a>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Articles </title>");
        addStyle(out);
        out.println("</head>");
        try {
            out.println("<h3>Articles...</h3>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Domain</th>");
            out.println("<th>Summary</th>");
            out.println("<th>Date</th>");
            out.println("<th>Link</th>");
            out.println("</tr>");
            List<Article> articles = articleRepository.read();
            for (Article article : articles) {
                out.println("<tr>");
                out.println("<td>"+article.getId()+"</td>");
                out.println("<td>"+article.getDomain()+"</td>");
                out.println("<td>"+article.getSummary()+"</td>");
                out.println("<td>"+article.getDate()+"</td>");
                out.println("<td><a target='_blank' href='"+article.getLink()+"'>open</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e) {
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        }
        addGoBack(out);
        out.close();
    }

    private void addStyle(PrintWriter out) {
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
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