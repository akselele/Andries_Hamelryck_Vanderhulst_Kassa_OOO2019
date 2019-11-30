package model.ObserverPattern;

import model.Artikel;

/**
 @Author Axel Hamelryck
 **/

public interface Subject {
    public void registerObserver(EventType e, Observer o);

    public void unregisterObserver(EventType e, Observer o);

    public void notifyObserver(EventType e, Artikel artikel, double uitkomst);
}
