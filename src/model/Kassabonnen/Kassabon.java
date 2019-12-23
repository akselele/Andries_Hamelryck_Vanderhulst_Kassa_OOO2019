package model.Kassabonnen;

import model.Artikel;

import java.util.Map;
/**
 * @author Noa Andries
 * @author Axel Hamelryck
 **/


public interface Kassabon {

    String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting);
}
