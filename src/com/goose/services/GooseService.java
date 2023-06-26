package com.goose.services;

import com.goose.concole.work.actions.Action;
import com.goose.concole.work.actions.FeedingAction;
import com.goose.concole.work.actions.WearingHatAction;
import com.goose.models.Food;
import com.goose.models.Goose;

import java.sql.SQLException;

public class GooseService {

    public GooseService() {
    }

    public void doAction(Action action, Goose goose) throws SQLException {
        Class actionClass = action.getClass();

        if (actionClass == FeedingAction.class) {
            goose.feedGoose(((FeedingAction) action).getFood());
        } else if (actionClass == FeedingAction.class) {

        } else if (actionClass == FeedingAction.class) {

        } else if (actionClass == WearingHatAction.class) {
            goose.wearHat(((WearingHatAction) action).getHat());
        }
    }



}
