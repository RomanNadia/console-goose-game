public class Goose {
    private String name;

    private int maxHunger;
    private int currentHunger;

    private int maxHygiene;
    private int currentHygiene;

    private int maxSatisfaction;
    private int currentSatisfaction;

    private int maxHealth;
    private int currentHealth;


    public Goose(String name, int max_hunger, int current_hunger, int max_hygiene, int current_hygiene,
                 int max_satisfaction, int current_satisfaction, int max_health, int current_health) {
        this.name = name;
        this.maxHunger = max_hunger;
        this.currentHunger = current_hunger;
        this.maxHygiene = max_hygiene;
        this.currentHygiene = current_hygiene;
        this.maxSatisfaction = max_satisfaction;
        this.currentSatisfaction = current_satisfaction;
        this.maxHealth = max_health;
        this.currentHealth = current_health;
    }

    @Override
    public String toString() {
        return "\n Goose " + name + "\n hunger:" + currentHunger + "/" + maxHunger +
                "\n hygiene:" + currentHygiene + "/" + maxHygiene +
                "\n satisfaction:" + currentSatisfaction + "/" + maxSatisfaction +
                "\n health:" + currentHealth + "/" + maxHealth;
    }

    public void feedGoose(Goose goose, int nutrition) { //Food
        int newCurrentHunger = goose.getCurrentHunger() + nutrition;
        if (newCurrentHunger > goose.getMaxHunger())
            newCurrentHunger = goose.getMaxHunger();
        goose.setCurrentHunger(newCurrentHunger);
        System.out.println("Goose was fed. \n a description: " + goose.toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    public int getCurrentHunger() {
        return currentHunger;
    }

    public void setCurrentHunger(int currentHunger) {
        this.currentHunger = currentHunger;
    }

    public int getMaxHygiene() {
        return maxHygiene;
    }

    public void setMaxHygiene(int maxHygiene) {
        this.maxHygiene = maxHygiene;
    }

    public int getCurrentHygiene() {
        return currentHygiene;
    }

    public void setCurrentHygiene(int currentHygiene) {
        this.currentHygiene = currentHygiene;
    }

    public int getMaxSatisfaction() {
        return maxSatisfaction;
    }

    public void setMaxSatisfaction(int maxSatisfaction) {
        this.maxSatisfaction = maxSatisfaction;
    }

    public int getCurrentSatisfaction() {
        return currentSatisfaction;
    }

    public void setCurrentSatisfaction(int currentSatisfaction) {
        this.currentSatisfaction = currentSatisfaction;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
