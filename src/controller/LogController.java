package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LogController {
    private String text = "";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public LogController() {
    }


    public void toString(LocalDateTime dt, double bedrag, double korting) {
        text += (dt.format(formatter) + " --> $" + bedrag + " - " + (bedrag - korting) + "= " + korting + "\n");
    }

    public String getText() {
        return text;
    }
}
