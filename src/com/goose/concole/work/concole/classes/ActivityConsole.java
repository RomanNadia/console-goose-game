package com.goose.concole.work.concole.classes;

import com.goose.info.from.db.ActivityInfo;
import com.goose.info.from.db.DetergentInfo;
import com.goose.models.Activity;
import com.goose.models.Detergent;

import java.sql.SQLException;
import java.util.HashMap;

public class ActivityConsole extends BaseConsole {

    public Activity chooseActivity() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose an activity:");
        HashMap<String, Activity> activities = ActivityInfo.getActivities();

        activities.forEach((key, value) -> {
            System.out.print(value.getName() + " - " + key + "\n");
        });

        String input = getCorrectAction("", activities.size());

        return activities.get(input);
    }
}
