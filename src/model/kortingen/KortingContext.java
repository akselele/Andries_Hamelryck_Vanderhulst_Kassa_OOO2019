package model.kortingen;

import model.WinkelMandje;

import java.util.ArrayList;
import java.util.List;

public class KortingContext {
    private KortingStrategy kortingStrategy;

    public KortingContext() {

    }

    public void setKortingStrategy(KortingStrategy kortingStrategy) {
        this.kortingStrategy = kortingStrategy;
    }

    public double getTotaleKorting(WinkelMandje winkelMandje){
       return kortingStrategy.getTotaleKorting(winkelMandje);
    }

    public String[] getKortingList(){
        List<String> kortingLijst = new ArrayList<String>();
        for (KortingType korting : KortingType.values()){
            kortingLijst.add(korting.toString());
        }
        String[] kortingLijstStr = new String[kortingLijst.size()];
        kortingLijstStr = kortingLijst.toArray(kortingLijstStr);
        return kortingLijstStr;
    }


}
