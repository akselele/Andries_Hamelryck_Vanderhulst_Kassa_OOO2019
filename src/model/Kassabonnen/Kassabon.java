package model.Kassabonnen;

import model.Artikel;

import java.util.Map;
/**
 * @Author Noa Andries
 *  @Author Axel Hamelryck
 **/


public interface Kassabon {

    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting);
}
