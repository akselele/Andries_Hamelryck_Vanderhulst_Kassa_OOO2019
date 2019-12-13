package model.Kassabonnen;

import model.Artikel;

import java.util.Map;

public interface Kassabon {

    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting, String x);
}
