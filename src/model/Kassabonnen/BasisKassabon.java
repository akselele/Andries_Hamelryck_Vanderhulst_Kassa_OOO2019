package model.Kassabonnen;

import model.Artikel;

import java.util.Map;

/**
 * @author Noa Andries
 * @author Axel Hamelryck
 **/

public class BasisKassabon implements Kassabon {

    public BasisKassabon() {

    }


    @Override
    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting) {
        String y = "";
        String x = "";
        for (Artikel artikel : artikelIntegerMap.keySet()) {
            x += artikel.getOmschrijving() + "\t\t        " + artikelIntegerMap.get(artikel) + "\t" + artikel.getPrijs() + "\t\n";
        }
        y = "Omschrijving\t\tAantal\t  Prijs\n" +
                "*****************************\n" +
                x +
                "*****************************\n" +
                "Totaal: (exclusief korting) : " + uitkomstZonderkorting + "€         \n" +
                "Betaald (inclusief korting) : " + uitkomstmetKorting + "€         \n";
        return y;
    }
}
