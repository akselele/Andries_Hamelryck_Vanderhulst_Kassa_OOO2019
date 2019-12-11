package model.kortingen;

/**
 * @Author Kasper Vanderhulst
 **/


public enum KortingType {
    GROEPKORTING(GroepKorting.class),
    DREMPELKORTING(DrempelKorting.class),
    DUURSTEKORTING(DuursteItemKorting.class);

    private Class kortingClass;


    KortingType(Class kortingClass){
        this.kortingClass = kortingClass;
    }

}