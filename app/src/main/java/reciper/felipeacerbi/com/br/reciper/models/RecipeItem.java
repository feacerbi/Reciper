package reciper.felipeacerbi.com.br.reciper.models;

/**
 * Created by Felipe on 2/29/2016.
 */
public class RecipeItem {

    private Ingredient ingredient;
    private int measure;
    private int unit;
    private long cartId;

    public RecipeItem() {
    }

    public RecipeItem(Ingredient ingredient, int measure, int unit) {
        this.ingredient = ingredient;
        this.measure = measure;
        this.unit = unit;
    }

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

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
}
