package my.apps.db;

import my.apps.domain.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author flo
 * @since 17/01/2017.
 */
public class ArticleRepository {

    // 1. define connection params to db
    final static String URL = "jdbc:postgresql://localhost:5432/lab17";
    final static String USERNAME = "postgres";
    final static String PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public void insert(Article article) throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO article( link, domain, summary, date) VALUES (?,?, ?, ?)");
        pSt.setString(1, article.getLink());
        pSt.setString(2, article.getDomain());
        pSt.setString(3, article.getSummary());
        pSt.setDate(4, article.getDate());

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
        ResultSet rs = st.executeQuery("SELECT id, link, summary, domain, date FROM article");

        // 5. iterate the result set and print the values
        List<Article> articles = new ArrayList<>();
        while (rs.next()) {
            Article article = new Article(
                    rs.getString("link"),
                    rs.getDate("date"),
                    rs.getString("summary"),
                    rs.getString("domain")
            );
            article.setId(rs.getLong("id"));
            articles.add(article);
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
        return articles;
    }
}
