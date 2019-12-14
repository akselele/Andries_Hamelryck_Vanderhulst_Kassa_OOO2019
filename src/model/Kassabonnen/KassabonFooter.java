package model.Kassabonnen;

import model.Artikel;

import java.util.Map;

public class KassabonFooter extends BasisKassabon implements Kassabon {

    public KassabonFooter() {
        super();
    }

    @Override
    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting, String x) {
        double zonderBTW = (uitkomstmetKorting/100)*6;
        return super.string(artikelIntegerMap, uitkomstmetKorting, uitkomstZonderkorting, x) + "\n*****************************\n" + "Prijs zonder btw:     " +
                Math.round(uitkomstmetKorting - zonderBTW) + " (BTW = " + Math.round(zonderBTW) + ")";
    }
}
