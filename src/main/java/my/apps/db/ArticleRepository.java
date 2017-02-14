package my.apps.db;

import my.apps.web.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author flo
 * @since 17/01/2017.
 */
public class ArticleRepository {

    // 1. define connection params to db
    final static String URL = "jdbc:postgresql://IP:5432/test";
    final static String USERNAME = "fasttrackit_dev";
    final static String PASSWORD = "fasttrackit_dev";

    public void insert(Article recipe) throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO article( link, domain, summary, date) VALUES (?,?, ?, ?)");
        pSt.setString(1, recipe.getLink());
        pSt.setString(2, recipe.getDomain());
        pSt.setString(3, recipe.getSummary());
        pSt.setString(4, recipe.getDate());

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    public List<Article> read() throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT link, summary, domain, date FROM article");

        // 5. iterate the result set and print the values
        List<Article> articles = new ArrayList<>();
        while (rs.next()) {
            Article article = new Article(
                    rs.getString("link"),
                    rs.getString("date"),
                    rs.getString("summary"),
                    rs.getString("domain")
            );
            articles.add(article);
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
        return articles;
    }
}
