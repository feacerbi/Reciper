package reciper.felipeacerbi.com.br.reciper.models;

/**
 * Created by Felipe on 3/1/2016.
 */
public class Category {

    public static int COURSES = 0;
    public static int DESSERTS = 1;
    public static int DRINKS = 2;

    private String name;

    public Category(int category) {

        switch (category) {
            case 0:
                name = "Courses";
                break;
            case 1:
                name = "Desserts";
                break;
            case 2:
                name = "Drinks";
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
