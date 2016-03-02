package reciper.felipeacerbi.com.br.reciper.models;

/**
 * Created by Felipe on 3/1/2016.
 */
public class Difficulty {

    public static int BEGINNER = 0;
    public static int CASUAL = 1;
    public static int ADVANCED = 2;
    public static int PROFESSIONAL = 3;
    public static int UNIQUE = 4;

    private String name;

    public Difficulty(int difficulty) {

        switch (difficulty) {
            case 0:
                name = "Beginner";
                break;
            case 1:
                name = "Casual";
                break;
            case 2:
                name = "Advanced";
                break;
            case 3:
                name = "Professional";
                break;
            case 4:
                name = "Unique";
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
