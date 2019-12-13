package model.Kassabonnen;

import model.Artikel;

import java.util.Map;

public class KassaBonHeader extends BasisKassabon implements Kassabon {

    public KassaBonHeader() {
        super();
    }


    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting, String x) {
        return x + "\n*****************************\n" + super.string(artikelIntegerMap, uitkomstmetKorting, uitkomstZonderkorting, x);
    }
}
