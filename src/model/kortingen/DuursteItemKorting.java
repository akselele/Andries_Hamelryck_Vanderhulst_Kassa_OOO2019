package model.kortingen;

/**
 * @Author Kasper Vanderhulst
 **/


public class DuursteItemKorting implements KortingStrategy {
    @Override
    public double getTotaleKorting(double prijs) {
        return 0;
    }
}