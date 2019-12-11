package model.kortingen;

import model.WinkelMandje;

/**
 * @Author Kasper Vanderhulst
 **/


public interface KortingStrategy {

    double getTotaleKorting(WinkelMandje winkelMandje);
}
