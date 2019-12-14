package model.kortingen;

import database.ArtikelTekstLoadSave;
import database.excel.ArtikelExcelLoadSaveStrategy;

import java.io.*;
import java.util.Properties;

/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/
public class KassaProperties {

    public static Properties load() throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/properties.properties");
        properties.load(is);
        is.close();
//        if(properties.getProperty("DREMPELKORTING").equalsIgnoreCase("TRUE")){
//
//        }
//        else if(properties.getProperty("GROEPKORTING").equalsIgnoreCase("TRUE")){
//
//        }
//        else if(properties.getProperty("DUURSTEITEMKORTING").equalsIgnoreCase("TRUE")){
//
//        }
        return properties;
    }


    //TODO VERANDER ALLE NAMEN VAN DE GETPROPERTIES IN DE 3 KORTINGSKLASSEN

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
