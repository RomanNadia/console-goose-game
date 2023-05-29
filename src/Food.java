import java.util.ArrayList;
import java.util.List;

public class Food {
    private String name;
    private int nutrition;

    public Food(String name, int nutrition) {
        this.name = name;
        this.nutrition = nutrition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNutrition() {
        return nutrition;
    }

    public void setNutrition(int nutrition) {
        this.nutrition = nutrition;
    }

    static class FoodAmount{
        static final List<Food> foods = new ArrayList<Food>() {{
            add(1, new Food("cookie", 10));
            add(2, new Food("marshmallow", 5));
            add(3, new Food("chocolate", 15));
        }};
    }

}
