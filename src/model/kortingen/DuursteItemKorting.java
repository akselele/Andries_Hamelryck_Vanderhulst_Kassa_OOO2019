package model.kortingen;

import model.Artikel;
import model.WinkelMandje;

import java.util.Map;

/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/


public class DuursteItemKorting implements KortingStrategy {


    @Override
    public double getTotaleKorting(WinkelMandje winkelMandje, int korting, int drempelwaarde, String group) {
        double totaal = winkelMandje.getTotaalprijs();
        totaal = totaal - winkelMandje.getDuurste().getPrijs() + (winkelMandje.getDuurste().getPrijs() - (winkelMandje.getDuurste().getPrijs()/100)*korting);
        return totaal;
    }
}
