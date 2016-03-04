package reciper.felipeacerbi.com.br.reciper.models;

/**
 * Created by Felipe on 2/29/2016.
 */
public class Ingredient {

    private long id;
    private String name;
    private String description;
    private String photoPath;

    public Ingredient() {
    }

    public Ingredient(String description, String name) {
        this.description = description;
        this.name = name;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
