package model.kortingen;
/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/
public class KortingFactory {
    public  KortingStrategy createKorting(String code){
        KortingType kortingEnum = KortingType.valueOf(code.toUpperCase());
        KortingStrategy kortingStrategy = null;
        try{
            Class dbClass = kortingEnum.getKortingClass();
            Object dbObject = dbClass.newInstance();
            kortingStrategy = (KortingStrategy) dbObject;
        }
        catch (Exception e){
            System.out.println("Excepetion message: " + e.getMessage());
        }

        return kortingStrategy;
    }
}
