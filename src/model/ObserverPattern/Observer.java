package model.ObserverPattern;

import model.Artikel;

/**
 @Author Axel Hamelryck
 **/

public interface Observer {

    public void update(Artikel artikel, double uitkomst);
}
