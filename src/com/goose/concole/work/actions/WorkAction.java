package com.goose.concole.work.actions;

public class WorkAction extends Action {
    private final int newGooseCoins;

    public WorkAction(int newGooseCoins) {
        this.newGooseCoins = newGooseCoins;
    }

    public int getNewGooseCoins() {
        return newGooseCoins;
    }
}
