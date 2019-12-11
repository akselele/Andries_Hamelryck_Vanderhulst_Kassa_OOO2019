package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author Kasper Vanderhulst
 * @Author Axel Hamelryck
 **/


public class WinkelMandje {
    private Map<Artikel,Integer> artikels;


    public WinkelMandje(Map<Artikel,Integer> artikels){
        setArtikels(artikels);
    }

    public Map<Artikel, Integer> getArtikels() {
        return artikels;
    }

    public void setArtikels(Map<Artikel, Integer> artikels) {
        this.artikels = artikels;
    }

    public Artikel getDuurste(){
        Artikel duurste = new Artikel("","","",0.00,0);
        Set<Artikel> artikelsSet = artikels.keySet();
        for(Artikel artikel: artikelsSet){
            if(artikel.getPrijs() > duurste.getPrijs()){
                duurste = artikel;
            }
        }
        return duurste;
    }

    public int getAantal(){
        int aantal = 0;

        for(Artikel artikel: artikels.keySet()){
            aantal += artikel.getPrijs() * artikels.get(artikel);
        }
        return aantal;
    }

    public Set<String> getArtikelgroepen(){
        Set<String> groep = new TreeSet<>();
        for(Artikel artikel : artikels.keySet()){
            groep.add(artikel.getArtikelgroep());
        }

        return groep;
    }
}
