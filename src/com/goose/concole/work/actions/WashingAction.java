package com.goose.concole.work.actions;

import com.goose.models.Detergent;

public class WashingAction extends Action {
    private final Detergent detergent;

    public WashingAction(Detergent detergent) {
        this.detergent = detergent;
    }

    public Detergent getDetergent() {
        return detergent;
    }
}
