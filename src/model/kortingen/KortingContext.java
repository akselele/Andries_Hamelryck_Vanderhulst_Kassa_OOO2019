package model.kortingen;

import model.WinkelMandje;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/

public class KortingContext {
    private KortingStrategy kortingStrategy;

    public KortingContext() {

    }

    public void setKortingStrategy(KortingStrategy kortingStrategy, int korting, int drempelwaarde) throws IOException {
        this.kortingStrategy = kortingStrategy;
        KassaProperties kassaProperties = new KassaProperties();
        kassaProperties.saveDrempelwaarde(true, korting, drempelwaarde);

    }

    public double getTotaleKorting(WinkelMandje winkelMandje, int korting, int drempelwaarde, String group){
       return kortingStrategy.getTotaleKorting(winkelMandje,korting, drempelwaarde,group);
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
