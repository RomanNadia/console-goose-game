package com.goose.concole.work.actions;

import com.goose.models.Food;

public class FeedingAction extends Action {
    private final Food food;

    public FeedingAction(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }


}
