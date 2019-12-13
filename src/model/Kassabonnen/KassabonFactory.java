package model.Kassabonnen;

import model.kortingen.KortingStrategy;
import model.kortingen.KortingType;

public class KassabonFactory {
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
