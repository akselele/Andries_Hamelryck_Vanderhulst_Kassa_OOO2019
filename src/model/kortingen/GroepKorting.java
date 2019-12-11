package model.kortingen;

import model.Artikel;
import model.WinkelMandje;

import java.util.Map;

/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/

public class GroepKorting implements KortingStrategy {



    @Override
    public double getTotaleKorting(WinkelMandje winkelMandje, int korting, int drempelwaarde, String group) {
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
