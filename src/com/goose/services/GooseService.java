package com.goose.services;

import com.goose.concole.work.actions.*;
import com.goose.models.Food;
import com.goose.models.Goose;

import java.sql.SQLException;

public class GooseService {

    public GooseService() {
    }

    public void doAction(Action action, Goose goose) throws SQLException {
        Class<?> actionClass = action.getClass();
        goose.updateCharacteristics();

        if (actionClass == FeedingAction.class) {
            goose.feedGoose(((FeedingAction) action).getFood());
        } else if (actionClass == WashingAction.class) {
            goose.washGoose(((WashingAction) action).getDetergent());
        } else if (actionClass == InteractingAction.class) {
            goose.interactWithGoose(((InteractingAction) action).getActivity());
        } else if (actionClass == WearingHatAction.class) {
            goose.wearHat(((WearingHatAction) action).getHat());
        } else if (actionClass == CheckAction.class) {
            System.out.println(goose);
        } else if (actionClass == WorkAction.class) {
            goose.setGooseCoins(((WorkAction) action).getNewGooseCoins());
        }
    }



}
