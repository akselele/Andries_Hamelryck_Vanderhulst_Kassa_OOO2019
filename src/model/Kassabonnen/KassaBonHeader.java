package model.Kassabonnen;

import model.Artikel;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
/**
 * @Author Noa Andries
 * @Author Axel Hamelryck
 **/

public class KassaBonHeader extends BasisKassabon implements Kassabon {
    private Properties properties;
    public KassaBonHeader() throws IOException {
        super();
        properties = KassabonProperties.load();
    }


    public String string(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting) {
        String x = properties.getProperty("HEADERTEXT");
        return x + "\n*****************************\n" + super.string(artikelIntegerMap, uitkomstmetKorting, uitkomstZonderkorting);
    }
}
