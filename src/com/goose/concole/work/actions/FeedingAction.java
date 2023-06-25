package com.goose.concole.work.actions;

import com.goose.models.Food;

public class FeedingAction extends Action {
    private Food food;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

}
