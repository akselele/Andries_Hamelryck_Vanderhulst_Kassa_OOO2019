package model.ObserverPattern;

import javafx.collections.ObservableList;
import model.Artikel;

import java.util.List;

/**
 @Author Axel Hamelryck
 **/

public interface Subject {
     void registerObserver(EventType e, Observer o);

     void unregisterObserver(EventType e, Observer o);

     void notifyObserver(EventType e, ObservableList<Artikel> artikels);
     void notifyObserver(EventType e, double artikels);
}
