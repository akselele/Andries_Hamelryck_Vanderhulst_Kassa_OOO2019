package model;

import java.util.Map;

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
        return null;
    }

    public int getAantal(){
        return 0;
    }
}
