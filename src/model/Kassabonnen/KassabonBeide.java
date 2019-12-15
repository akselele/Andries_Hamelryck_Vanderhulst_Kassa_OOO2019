package model.Kassabonnen;

import model.Artikel;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Properties;
/**
 * @Author Noa Andries
 *  @Author Axel Hamelryck
 **/

public class KassabonBeide extends BasisKassabon implements Kassabon {
    private Properties properties;

    public KassabonBeide() throws IOException {
        super();
        properties = KassabonProperties.load();
    }

    @Override
    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting) {
        String x = properties.getProperty("HEADERTEXT");
        DecimalFormat f = new DecimalFormat("##.00");
        double zonderBTW = (uitkomstmetKorting/100)*6;
        return x + "\n*****************************\n" + super.string(artikelIntegerMap, uitkomstmetKorting, uitkomstZonderkorting) + "\n*****************************\n" + "Prijs zonder btw:     " +
                (uitkomstmetKorting - zonderBTW) + " (BTW = " + f.format(zonderBTW) + ")";
    }
}
