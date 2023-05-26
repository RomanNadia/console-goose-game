public class User_action {

    public User_action() {
    }

    public void feedGoose(Goose goose, int nutrition) { //Food
        int new_current_hunger = goose.getCurrent_hunger() + nutrition;
        if (new_current_hunger > goose.getMax_hunger())
            new_current_hunger = goose.getMax_hunger();
        goose.setCurrent_hunger(new_current_hunger);
        System.out.println("Goose was fed. \n a description: " + goose.toString());
    }
}
