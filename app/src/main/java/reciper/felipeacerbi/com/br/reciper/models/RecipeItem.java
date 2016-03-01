package reciper.felipeacerbi.com.br.reciper.models;

/**
 * Created by Felipe on 2/29/2016.
 */
public class RecipeItem {

    private Ingredient ingredient;
    private int measure;
    private Unit unit;
    private long recipeId;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}
