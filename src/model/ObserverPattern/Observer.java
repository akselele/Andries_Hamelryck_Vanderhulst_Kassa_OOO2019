package model.ObserverPattern;

import javafx.collections.ObservableList;
import model.Artikel;

import java.util.List;

/**
 @Author Axel Hamelryck
 **/

public interface Observer {

    public void update(ObservableList<Artikel> artikels);

    public void update(double uitkomst);
}
