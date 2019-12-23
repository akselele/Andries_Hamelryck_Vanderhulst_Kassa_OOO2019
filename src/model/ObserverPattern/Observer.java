package model.ObserverPattern;

import javafx.collections.ObservableList;
import model.Artikel;

import java.util.List;

/**
 @Author Axel Hamelryck
 **/

public interface Observer {

     void update(ObservableList<Artikel> artikels);

     void update(double uitkomst);
}
