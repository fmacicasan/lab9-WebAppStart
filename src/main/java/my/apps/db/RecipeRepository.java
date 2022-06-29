package my.apps.db;

import my.apps.domain.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author flo
 * @since 17/01/2017.
 */
public class RecipeRepository {

    // 1. define connection params to db
    final static String URL = "jdbc:postgresql://localhost:5432/lab17";
    final static String USERNAME = "postgres";
    final static String PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public void insert(Recipe recipe) throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO recipes( name, description, ingredients, duration) VALUES (?,?, ?, ?)");
        pSt.setString(1, recipe.getName());
        pSt.setString(2, recipe.getDescription());
        pSt.setString(3, recipe.getIngredients());
        pSt.setString(4, recipe.getDuration());

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    public List<Recipe> read() throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT name, description, duration, ingredients FROM recipes");

        // 5. iterate the result set and print the values
        List<Recipe> recipes = new ArrayList<>();
        while (rs.next()) {
            Recipe recipe = new Recipe(
                  rs.getString("ingredients"),
                    rs.getString("description"),
                    rs.getString("name"),
                    rs.getString("duration")
            );
            recipes.add(recipe);
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
        return recipes;
    }
}
