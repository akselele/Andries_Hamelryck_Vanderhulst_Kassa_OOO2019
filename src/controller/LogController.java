package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LogController {
    private String text = "";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public LogController(){

    }

    //TODO hier moet nog korting en totaalbedrag bij.
    public void toString(LocalDateTime dt, double bedrag){
        text += (dt.format(formatter) + " - $" + bedrag + "\n");
    }

    public String getText() {
        return text;
    }
}