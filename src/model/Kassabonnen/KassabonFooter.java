package model.Kassabonnen;

import model.Artikel;

import java.text.DecimalFormat;
import java.util.Map;
/**
 * @author Noa Andries
 * @author Axel Hamelryck
 **/

public class KassabonFooter extends BasisKassabon implements Kassabon {

    public KassabonFooter() {
        super();
    }

    @Override
    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting) {
        DecimalFormat f = new DecimalFormat("##.00");
        double zonderBTW = (uitkomstmetKorting/100)*6;
        return super.string(artikelIntegerMap, uitkomstmetKorting, uitkomstZonderkorting) + "\n*****************************\n" + "Prijs zonder btw:     " +
                Math.round(uitkomstmetKorting - zonderBTW) + " (BTW = " + f.format(zonderBTW) + ")";
    }
}
