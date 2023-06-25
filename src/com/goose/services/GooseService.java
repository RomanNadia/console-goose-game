package com.goose.services;

import com.goose.concole.work.actions.Action;
import com.goose.concole.work.actions.FeedingAction;
import com.goose.models.Food;
import com.goose.models.Goose;

import java.sql.SQLException;

public class GooseService {

    public GooseService() {
    }

    public void doAction(Action action, Goose goose) throws SQLException {
        Class actionClass = action.getClass();

        if (actionClass == FeedingAction.class) {
            goose.feedGoose(((FeedingAction) action).getFood().getNutrition());
        } else if (actionClass == FeedingAction.class) {

        } else if (actionClass == FeedingAction.class) {

        } else if (actionClass == FeedingAction.class) {

        }
    }



}
