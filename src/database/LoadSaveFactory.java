package database;
/**
 @Author Noa Andries
 **/
public class LoadSaveFactory {

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
