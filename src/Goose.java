public class Goose {
    private String name;

    private int max_hunger;
    private int current_hunger;

    private int max_hygiene;
    private int current_hygiene;

    private int max_satisfaction;
    private int current_satisfaction;

    private int max_health;
    private int current_health;


    public Goose(String name, int max_hunger, int current_hunger, int max_hygiene, int current_hygiene,
                 int max_satisfaction, int current_satisfaction, int max_health, int current_health) {
        this.name = name;
        this.max_hunger = max_hunger;
        this.current_hunger = current_hunger;
        this.max_hygiene = max_hygiene;
        this.current_hygiene = current_hygiene;
        this.max_satisfaction = max_satisfaction;
        this.current_satisfaction = current_satisfaction;
        this.max_health = max_health;
        this.current_health = current_health;
    }

    @Override
    public String toString() {
        return "\n Goose " + name + "\n hunger:" + current_hunger + "/" + max_hunger +
                "\n hygiene:" + current_hygiene + "/" + max_hygiene +
                "\n satisfaction:" + current_satisfaction + "/" + max_satisfaction +
                "\n health:" + current_health + "/" + max_health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax_hunger() {
        return max_hunger;
    }

    public void setMax_hunger(int max_hunger) {
        this.max_hunger = max_hunger;
    }

    public int getCurrent_hunger() {
        return current_hunger;
    }

    public void setCurrent_hunger(int current_hunger) {
        this.current_hunger = current_hunger;
    }

    public int getMax_hygiene() {
        return max_hygiene;
    }

    public void setMax_hygiene(int max_hygiene) {
        this.max_hygiene = max_hygiene;
    }

    public int getCurrent_hygiene() {
        return current_hygiene;
    }

    public void setCurrent_hygiene(int current_hygiene) {
        this.current_hygiene = current_hygiene;
    }

    public int getMax_satisfaction() {
        return max_satisfaction;
    }

    public void setMax_satisfaction(int max_satisfaction) {
        this.max_satisfaction = max_satisfaction;
    }

    public int getCurrent_satisfaction() {
        return current_satisfaction;
    }

    public void setCurrent_satisfaction(int current_satisfaction) {
        this.current_satisfaction = current_satisfaction;
    }

    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    public int getCurrent_health() {
        return current_health;
    }

    public void setCurrent_health(int current_health) {
        this.current_health = current_health;
    }
}
