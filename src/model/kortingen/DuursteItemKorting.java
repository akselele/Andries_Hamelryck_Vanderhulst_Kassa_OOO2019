package model.kortingen;

import model.Artikel;
import model.WinkelMandje;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/


public class DuursteItemKorting implements KortingStrategy {
    private Properties properties;

    public DuursteItemKorting() throws IOException {
        properties = KassaProperties.load();
    }


    @Override
    public double getTotaleKorting(WinkelMandje winkelMandje) {
       int korting = Integer.parseInt(properties.getProperty("DUURSTEKORTINGWAARDE"));
        System.out.println(korting);

        double totaal = winkelMandje.getTotaalprijs();
        totaal = totaal - winkelMandje.getDuurste().getPrijs() + (winkelMandje.getDuurste().getPrijs() - (winkelMandje.getDuurste().getPrijs()/100)*korting);
        return totaal;
    }
}
