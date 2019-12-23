package model.kortingen;

import database.ArtikelTekstLoadSave;
import database.excel.ArtikelExcelLoadSaveStrategy;

import java.io.*;
import java.util.Properties;

/**
 * @author Kasper Vanderhulst
 * @author Noa Andries
 **/
public class KassaProperties {

    //laad de properties file in en returnt deze
    public static Properties load() throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/properties.properties");
        properties.load(is);
        is.close();
        return properties;
    }

    //savet welke korting er moet gegeven worden in de properties file (in dit geval drempelkorting), ook de drempelwaarde en het % van de korting worden bijgehouden
    public void saveDrempelKorting(int korting, int drempelwaarde) throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("DREMPELKORTING", "TRUE");
        properties.setProperty("GROEPKORTING", "FALSE");
        properties.setProperty("DUURSTEKORTING", "FALSE");
        properties.setProperty("DREMPELKORTINGWAARDE", String.valueOf(korting));
        properties.setProperty("DREMPELWAARDE", String.valueOf(drempelwaarde));
        properties.store(out, null);
        out.close();
    }

    //savet welke korting er moet gegeven worden in de properties file(in dit geval groepskorting), ook het % van de korting en de groep waarop wordt bijgehouden
    public void saveGroepKorting(int korting, String groep) throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("GROEPKORTING", "TRUE");
        properties.setProperty("DREMPELKORTING", "FALSE");
        properties.setProperty("DUURSTEKORTING", "FALSE");
        properties.setProperty("GROEPKORTINGWAARDE", String.valueOf(korting));
        properties.setProperty("GROEP", groep);
        properties.store(out, null);
        out.close();
    }

    //savet welke korting er moet gegeven worden in de properties file (in dit geval duursteitemkorting), ook het % van korting wordt bijgehouden
    public void saveDuursteItemKorting(int korting) throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("DUURSTEKORTING", "TRUE");
        properties.setProperty("DREMPELKORTING", "FALSE");
        properties.setProperty("GROEPKORTING", "FALSE");
        properties.setProperty("DUURSTEKORTINGWAARDE", String.valueOf(korting));
        properties.store(out, null);
        out.close();
    }

}
