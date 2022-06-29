package my.apps.web;
import my.apps.db.RecipeRepository;
import my.apps.domain.Recipe;
import my.apps.service.RecipeService;

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

@WebServlet("/recipes")
public class SomeServlet extends HttpServlet {

    private int counter;

    private RecipeService recipeService = new RecipeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String ingredients = request.getParameter("ingredients");
        String duration = request.getParameter("duration");



        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            Recipe newRecipe = recipeService.insert(ingredients, description, name, duration);
            out.println("inserted - <b>" + newRecipe.toString() + "</b><br/>");

        } catch (ClassNotFoundException e) {
            out.println("Class not found issues!");
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("SQL exception issues!");
            e.printStackTrace();
        }
        out.println("<a href='/'>Go Back</a>");
        // finished writing, send to browser
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        List<Recipe> recipes;
        try {
            recipes = recipeService.listAll();
        } catch (Exception e) {
            recipes = new ArrayList<>();
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Get count </title>");
        out.println("</head>");

        out.println("<h2>Get count</h2>");
        for(Recipe recipe : recipes) {
            out.println("<b>" + recipe.toString() + "</b><br />");
        }
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