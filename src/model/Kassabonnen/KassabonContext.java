package model.Kassabonnen;

import model.Artikel;
import model.WinkelMandje;
import model.kortingen.KassaProperties;
import model.kortingen.KortingStrategy;
import model.kortingen.KortingType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
/**
 * @author Noa Andries
 * @author Axel Hamelryck
 **/

public class KassabonContext {
    private Kassabon kassabon;
    private Properties properties;

    public KassabonContext()
    {
        String x = "";
        String[] kassabonnen = getKassabonList();
        KassabonFactory kassabonFactory = KassabonFactory.getInstance();
        try {
            properties = KassabonProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (properties.getProperty("HEADER").equalsIgnoreCase("true")) {
            setKassabon(kassabonFactory.createKassabon(kassabonnen[0]));
        }
        if (properties.getProperty("FOOTER").equalsIgnoreCase("true")) {
           setKassabon(kassabonFactory.createKassabon(kassabonnen[1]));
        }
        if (properties.getProperty("BEIDE").equalsIgnoreCase("true")) {
            setKassabon(kassabonFactory.createKassabon(kassabonnen[2]));
        }
        if(properties.getProperty("BEIDE").equalsIgnoreCase("false")  && properties.getProperty("FOOTER").equalsIgnoreCase("false")
                && properties.getProperty("HEADER").equalsIgnoreCase("false")){
            setKassabon(new BasisKassabon());
        }
    }
    public void setKassabon(Kassabon kassabon){
        this.kassabon = kassabon;
    }

    public void setKassabonProperties(Kassabon kassabon) throws IOException {

        this.kassabon = kassabon;

        KassabonProperties kassabonProperties = new KassabonProperties();
        System.out.println(kassabon.getClass().getName());
        if(kassabon.getClass().getName().equalsIgnoreCase("model.Kassabonnen.KassabonHeader")){
            kassabonProperties.saveHeader();
        }
        if(kassabon.getClass().getName().equalsIgnoreCase("model.Kassabonnen.KassabonFooter")){
            kassabonProperties.saveFooter();
        }
        if(kassabon.getClass().getName().equalsIgnoreCase("model.Kassabonnen.kassabonBeide")){
            kassabonProperties.saveBeide();
        }
    }

    public String getKassabon(Map<Artikel, Integer> artikelIntegerMap, double uitkomstmetKorting, double uitkomstZonderkorting){
        return kassabon.string(artikelIntegerMap, uitkomstmetKorting, uitkomstZonderkorting);
    }

    public String[] getKassabonList(){
        List<String> kassabonLijst = new ArrayList<String>();
        for (KassabonType kassabon : KassabonType.values()){
            kassabonLijst.add(kassabon.toString());
        }
        String[] kassabonLijsStr = new String[kassabonLijst.size()];
        kassabonLijsStr = kassabonLijst.toArray(kassabonLijsStr);
        return kassabonLijsStr;
    }
}
