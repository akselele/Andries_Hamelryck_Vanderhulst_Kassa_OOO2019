package model.kortingen;

/**
 * @Author Kasper Vanderhulst
 **/

public class GroepKorting implements KortingStrategy {
    @Override
    public double getTotaleKorting(double prijs) {
        return 0;
    }
}
