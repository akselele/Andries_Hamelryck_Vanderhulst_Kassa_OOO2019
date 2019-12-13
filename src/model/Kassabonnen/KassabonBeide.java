package model.Kassabonnen;

import model.Artikel;

import java.util.Map;

public class KassabonBeide extends BasisKassabon implements Kassabon {
    public KassabonBeide() {
        super();
    }

    @Override
    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting, String x) {
        double zonderBTW = (uitkomstmetKorting/100)*6;
        return x + "\n*****************************\n" + super.string(artikelIntegerMap, uitkomstmetKorting, uitkomstZonderkorting, x) + "\n*****************************\n" + "Prijs zonder btw:     " +
                (uitkomstmetKorting - zonderBTW) + " (BTW = " + zonderBTW + ")";
    }
}
