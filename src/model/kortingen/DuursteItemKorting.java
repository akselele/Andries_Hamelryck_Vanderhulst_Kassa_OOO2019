package model.kortingen;

import model.WinkelMandje;

/**
 * @Author Kasper Vanderhulst
 **/


public class DuursteItemKorting implements KortingStrategy {
    @Override
    public double getTotaleKorting(WinkelMandje winkelMandje) {
        return 0.0;
    }
}
