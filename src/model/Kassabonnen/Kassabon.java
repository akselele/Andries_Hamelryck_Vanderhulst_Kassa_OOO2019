package model.Kassabonnen;

import model.Artikel;

import java.util.Map;

public interface Kassabon {

    public void string(Map<Artikel, Integer> artikelIntegerMap, double uitkomst);
}
