package model.kortingen;

import model.WinkelMandje;

/**
 * @Author Kasper Vanderhulst
 **/

public class DrempelKorting implements KortingStrategy {
    @Override
    public double getTotaleKorting(WinkelMandje winkelMandje) {
        return 0.0;
    }
}
