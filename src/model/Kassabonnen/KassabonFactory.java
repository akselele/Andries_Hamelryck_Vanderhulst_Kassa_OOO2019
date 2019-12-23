package model.Kassabonnen;

import model.kortingen.KortingStrategy;
import model.kortingen.KortingType;

/**
 @Author Kasper Vanderhulst
 **/


public class KassabonFactory {
    private static KassabonFactory kassabonFactory;

    private KassabonFactory(){}

    public static KassabonFactory getInstance(){
        if(kassabonFactory == null){
            kassabonFactory = new KassabonFactory();
        }
        return kassabonFactory;
    }

    public Kassabon createKassabon(String code) {
        KassabonType kassabonEnum = KassabonType.valueOf(code.toUpperCase());
        Kassabon kassabon = null;
        try {
            Class dbClass = kassabonEnum.getKassabonClass();
            Object dbObject = dbClass.newInstance();
            kassabon = (Kassabon) dbObject;
        } catch (Exception e) {
            System.out.println("Excepetion message: " + e.getMessage());
            e.printStackTrace();
        }

        return kassabon;
    }
}
