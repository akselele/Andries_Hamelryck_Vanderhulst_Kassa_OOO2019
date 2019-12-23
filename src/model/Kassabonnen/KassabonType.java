package model.Kassabonnen;

import model.kortingen.DrempelKorting;
import model.kortingen.DuursteItemKorting;
import model.kortingen.GroepKorting;
/**
 @author Axel Hamelryck
 **/



public enum KassabonType {

    HEADERKASSABON(KassaBonHeader.class),
    FOOTERKASSABON(KassabonFooter.class),
    BEIDEKASSABON(KassabonBeide.class);

    private Class kassabonClass;


    KassabonType(Class kassabonClass){
        this.kassabonClass = kassabonClass;
    }

    public String getOmschrijving() { return kassabonClass.toString(); }

    public Class getKassabonClass() {
        return kassabonClass;
    }
}
