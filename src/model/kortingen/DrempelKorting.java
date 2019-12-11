package model.kortingen;

import model.Artikel;
import model.WinkelMandje;

import java.util.Map;

/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/

public class DrempelKorting implements KortingStrategy {


    @Override
    public double getTotaleKorting(WinkelMandje winkelMandje, int korting, int drempelwaarde, String group) {
        double totaal = winkelMandje.getTotaalprijs();
        if (totaal >= drempelwaarde)
        {
            totaal = totaal - (totaal/100)*korting;
        }
        return totaal;
    }
}
