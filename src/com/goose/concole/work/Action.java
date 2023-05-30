package com.goose.concole.work;

public class Action {
    String actionType;
    String additionalAttribute;

    public Action(String actionType) {
        this.actionType = actionType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAdditionalAttribute() {
        return additionalAttribute;
    }

    public void setAdditionalAttribute(String additionalAttribute) {
        this.additionalAttribute = additionalAttribute;
    }
}
