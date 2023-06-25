package com.goose.concole.work.actions;

public class Action { //maybe abstract too
    protected String actionType;

    public Action() {
    }

    public Action(String actionType) {
        this.actionType = actionType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

}


//public class Animal {
//    String name;
//}
//
//public class Panda extends Animal {
//    int speedGruztu;
//}
//
//public class Gadzila extends Animal {
//    int angreeness;
//}
//
//public Animal chooseAnaimal() {
//    String consoleInput = input.next(); //int
//    if(consoleInput.equals("1")) {
//        return new Panda();
//    }
//}