package com.goose.concole.work.concole.classes;

import com.goose.info.from.db.DetergentInfo;
import com.goose.models.Detergent;

import java.sql.SQLException;
import java.util.HashMap;

public class DetergentConsole extends BaseConsole {

    public Detergent chooseDetergent() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, happy user, please choose a food:");
        HashMap<String, Detergent> detergents = DetergentInfo.getDetergents();

        detergents.forEach((key, value) -> {
            System.out.print(value.getName() + " - " + key + "\n");
        });

        String input = getCorrectAction("", detergents.size());

        return detergents.get(input);
    }
}
