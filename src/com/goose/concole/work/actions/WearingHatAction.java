package com.goose.concole.work.actions;

import com.goose.models.Hat;

public class WearingHatAction extends Action {
    private final Hat hat;

    public WearingHatAction(Hat hat) {
        this.hat = hat;
    }

    public Hat getHat() {
        return hat;
    }


}
