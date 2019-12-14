package model.kortingen;

import model.WinkelMandje;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/

public class KortingContext {
    private KortingStrategy kortingStrategy;
    private Properties properties;


    public KortingContext(){
        String[] kortingen = getKortingList();
        KortingFactory kortingFactory = new KortingFactory();
        try {
            properties = KassaProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (properties.getProperty("GROEPKORTING").equalsIgnoreCase("true")) {
            setKortingStrategy(kortingFactory.createKorting(kortingen[0]));
        }
        if (properties.getProperty("DREMPELKORTING").equalsIgnoreCase("true")) {
            setKortingStrategy(kortingFactory.createKorting(kortingen[1]));
        }
        if (properties.getProperty("DUURSTEKORTING").equalsIgnoreCase("true")) {
            setKortingStrategy(kortingFactory.createKorting(kortingen[2]));

        }


    }

    public KortingStrategy getKortingStrategy() {
        return kortingStrategy;
    }

    public void setKortingStrategy(KortingStrategy kortingStrategy){
        this.kortingStrategy = kortingStrategy;
    }

    public void setKortingStrategyProperties(KortingStrategy kortingStrategy,int kortingprocent,String extraWaarde) throws IOException {

        this.kortingStrategy = kortingStrategy;

        KassaProperties kassaProperties = new KassaProperties();
        if(kortingStrategy.getClass().getName().equalsIgnoreCase("model.kortingen.DrempelKorting")){
            //TODO makes this for all kortingen so it saves all given values, values atm are just true false rest is hardcoded
            /*TODO als men drempelkorting aanduidt geeft het een numberformatexception omdat het GR1 wilt opslaan bij de
            DREMPWELWAARDE (terwijl dat daar eigenlijk niets mee heeft te maken) */
            kassaProperties.saveDrempelKorting(kortingprocent,Integer.parseInt(extraWaarde));
        }
        if(kortingStrategy.getClass().getName().equalsIgnoreCase("model.kortingen.DuursteItemKorting")){
            kassaProperties.saveDuursteItemKorting(kortingprocent);
        }
        if(kortingStrategy.getClass().getName().equalsIgnoreCase("model.kortingen.GroepKorting")){
            kassaProperties.saveGroepKorting(kortingprocent,extraWaarde);
        }
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
