package reciper.felipeacerbi.com.br.reciper.models;

import java.util.HashMap;

/**
 * Created by Felipe on 2/29/2016.
 */
public class Recipe {

    private long id;
    private String name;
    private String description;
    private String howTo;
    private String photoPath;
    private int portions;
    private String time;
    private Difficulty difficulty;
    private Category category;

    public Recipe() {
    }

    public Recipe(String name, String description, int portions, String time, Difficulty difficulty, Category category) {
        this.name = name;
        this.portions = portions;
        this.time = time;
        this.difficulty = difficulty;
        this.category = category;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowTo() {
        return howTo;
    }

    public void setHowTo(String howTo) {
        this.howTo = howTo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
