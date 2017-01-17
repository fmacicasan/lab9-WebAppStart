package my.apps;

/**
 * @author flo
 * @since 17/01/2017.
 */
public class Recipe {

    private String name;
    private String description;
    private String ingredients;
    private String duration;

    public Recipe(String ingredients, String description, String name, String duration) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Recipe: name =" +  name + " description = " + description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public String getDuration() {
        return duration;
    }
}
