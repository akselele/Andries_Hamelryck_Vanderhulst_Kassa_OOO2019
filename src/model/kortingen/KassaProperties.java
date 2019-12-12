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

    public void load() throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/properties.properties");
        properties.load(is);
        if(properties.getProperty("DrempelKorting").equalsIgnoreCase("TRUE")){

        }
        else if(properties.getProperty("GroepKorting").equalsIgnoreCase("TRUE")){

        }
        else if(properties.getProperty("DuursteItemKorting").equalsIgnoreCase("TRUE")){

        }
    }

    public void saveDrempelKorting(int korting, int drempelwaarde) throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("DrempelKorting", "TRUE");
        properties.setProperty("GroepKorting", "FALSE");
        properties.setProperty("DuursteItemKorting", "FALSE");
        properties.setProperty("Drempelkorting", String.valueOf(korting));
        properties.setProperty("Drempelwaarde", String.valueOf(drempelwaarde));
        properties.store(out, null);
        out.close();
    }

    public void saveGroepKorting(int korting, String groep) throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("GroepKorting", "TRUE");
        properties.setProperty("DrempelKorting", "FALSE");
        properties.setProperty("DuursteItemKorting", "FALSE");
        properties.setProperty("Groepkorting", String.valueOf(korting));
        properties.setProperty("Groep", groep);
        properties.store(out, null);
        out.close();
    }

    public void saveDuursteItemKorting(int korting) throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("DuursteItemKorting", "TRUE");
        properties.setProperty("DrempelKorting", "FALSE");
        properties.setProperty("GroepKorting", "FALSE");
        properties.setProperty("DuursteItemkorting", String.valueOf(korting));
        properties.store(out, null);
        out.close();
    }

}
