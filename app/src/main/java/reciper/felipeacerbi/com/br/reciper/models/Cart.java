package reciper.felipeacerbi.com.br.reciper.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by felipe.acerbi on 04/03/2016.
 */
public class Cart {

    private long id;
    private String name;
    private Calendar dayTime;
    private List<RecipeItem> recipeItems;

    public Cart() {
        recipeItems = new ArrayList<>();
    }

    public Calendar getDayTime() {
        return dayTime;
    }

    public void setDayTime(Calendar dayTime) {
        this.dayTime = dayTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecipeItem> getRecipeItems() {
        return recipeItems;
    }

    public void setRecipeItems(List<RecipeItem> recipeItems) {
        this.recipeItems = recipeItems;
    }
}
