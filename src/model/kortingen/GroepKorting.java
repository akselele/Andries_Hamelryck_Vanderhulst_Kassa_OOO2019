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

public class GroepKorting implements KortingStrategy {
    private Properties properties;

    public GroepKorting() throws IOException {
        properties = KassaProperties.load();
    }



    @Override
    public double getTotaleKorting(WinkelMandje winkelMandje) {
        String group = properties.getProperty("GROEP");
        int korting = Integer.parseInt(properties.getProperty("GROEPKORTINGWAARDE"));



        double totaal = 0;
        for (Map.Entry<Artikel, Integer> entry : winkelMandje.getArtikels().entrySet())
        {
           String agroup= entry.getKey().getArtikelgroep();
           double prijs = entry.getKey().getPrijs()*entry.getValue();
           if (agroup.equalsIgnoreCase(group))
           {
               totaal += prijs - (prijs/100)*korting;
           }
           else
           {
               totaal += prijs;
           }
        }
        return totaal;
    }
}
