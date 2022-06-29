package my.apps.service;

import my.apps.db.RecipeRepository;
import my.apps.domain.Recipe;

import java.sql.SQLException;
import java.util.List;

/**
 * @author flo
 * @since 22.06.2022.
 */
public class RecipeService {
    private RecipeRepository recipeRepository = new RecipeRepository();

    public Recipe insert(String ingredients, String description, String name, String duration) throws SQLException, ClassNotFoundException {
        Recipe newRecipe = new Recipe(ingredients, description, name, duration);
        recipeRepository.insert(newRecipe);
        return newRecipe;
    }

    public List<Recipe> listAll() throws SQLException, ClassNotFoundException {
        return recipeRepository.read();
    }
}
