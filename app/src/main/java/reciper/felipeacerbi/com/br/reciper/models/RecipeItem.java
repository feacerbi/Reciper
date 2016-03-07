package reciper.felipeacerbi.com.br.reciper.models;

/**
 * Created by Felipe on 2/29/2016.
 */
public class RecipeItem {

    private Ingredient ingredient;
    private int quantity;
    private String unit;
    private long cartId;

    public RecipeItem() {
    }

    public RecipeItem(Ingredient ingredient, int quantity, String unit) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int measure) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
}
