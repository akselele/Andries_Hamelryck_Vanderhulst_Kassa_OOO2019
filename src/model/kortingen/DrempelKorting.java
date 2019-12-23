package model.kortingen;

import model.Artikel;
import model.WinkelMandje;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author Kasper Vanderhulst
 * @author Noa Andries
 **/

public class DrempelKorting implements KortingStrategy {
    private Properties properties;

    public DrempelKorting() throws IOException {
        properties = KassaProperties.load();
    }


    @Override
    public double getTotaleKorting(WinkelMandje winkelMandje) {
        int korting = Integer.parseInt(properties.getProperty("DREMPELKORTINGWAARDE"));
        int drempelWaarde = Integer.parseInt(properties.getProperty("DREMPELWAARDE"));

        double totaal = winkelMandje.getTotaalprijs();
        if (totaal >= drempelWaarde)
        {
            totaal = totaal - (totaal/100)*korting;
        }
        return totaal;

    }


}
