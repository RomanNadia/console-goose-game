public class Food {
    private String name;
    private int nutrition;

    public Food(String name, int nutrition) { //where should I create food objects?
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
}
