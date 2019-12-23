package database;
/**
 @Author Noa Andries
 @Author Kasper Vanderhulst
 **/
public class LoadSaveFactory {
    private static LoadSaveFactory loadSaveFactoryUniqueInstance;

    private LoadSaveFactory(){ }
//singleton
    public static LoadSaveFactory getInstance(){
        if(loadSaveFactoryUniqueInstance == null){
            loadSaveFactoryUniqueInstance = new LoadSaveFactory();
        }
        return loadSaveFactoryUniqueInstance;
    }

    public LoadSaveStrategy getStrategy(String type)
    {
        LoadSaveStrategy result = null;
        type = type.toUpperCase();
        LoadSaveEnum strategy = LoadSaveEnum.valueOf(type);
        try {
            Class loadStrategy = strategy.getKlasse();
            Object strategyObject = loadStrategy.newInstance();
            result = (LoadSaveStrategy) strategyObject;
        } catch (IllegalAccessException | InstantiationException e ) {
            e.printStackTrace();
        }
        return result;
    }

}
