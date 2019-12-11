package model.kortingen;

public class KortingFactory {
    public static KortingStrategy createKorting(String code){
        KortingType kortingEnum = KortingType.valueOf(code.toUpperCase());
        String klasseNaam = kortingEnum.getOmschrijving();
        KortingStrategy kortingStrategy = null;
        try{
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            kortingStrategy = (KortingStrategy) dbObject;
        }
        catch (Exception e){}
        return kortingStrategy;
    }
}
