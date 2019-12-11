package model.ObserverPattern;

import javafx.collections.ObservableList;
import model.Artikel;

import java.util.List;

/**
 @Author Axel Hamelryck
 **/

public interface Subject {
    public void registerObserver(EventType e, Observer o);

    public void unregisterObserver(EventType e, Observer o);

    public void notifyObserver(EventType e, ObservableList<Artikel> artikels);
    public void notifyObserver(EventType e, double artikels);
}
