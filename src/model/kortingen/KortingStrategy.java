package model.kortingen;

import model.WinkelMandje;

/**
 * @author Kasper Vanderhulst
 **/


public interface KortingStrategy {

    double getTotaleKorting(WinkelMandje winkelMandje);
}
