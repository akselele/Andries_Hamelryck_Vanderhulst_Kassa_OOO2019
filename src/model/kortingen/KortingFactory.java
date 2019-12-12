package model.kortingen;
/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/
public class KortingFactory {
    public  KortingStrategy createKorting(String code){
        System.out.println("got in factory");
        KortingType kortingEnum = KortingType.valueOf(code.toUpperCase());
        KortingStrategy kortingStrategy = null;
        try{
            Class dbClass = kortingEnum.getKortingClass();
            Object dbObject = dbClass.newInstance();
            kortingStrategy = (KortingStrategy) dbObject;
            System.out.println(kortingStrategy);
        }
        catch (Exception e){
            System.out.println("Excepetion message: " + e.getMessage());
        }

        return kortingStrategy;
    }
}
