package com.goose.concole.work.actions;

import com.goose.models.Activity;

public class InteractingAction extends Action {
    private final Activity activity;

    public InteractingAction(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
