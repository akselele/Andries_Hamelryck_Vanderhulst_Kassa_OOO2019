package model.Kassabonnen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KassabonProperties {
    /**
     * @author Axel Hamelryck
     * @author Kasper Vanderhulst
     **/


    //returnt de eigenschappen die in de properties file zitten
    public static Properties load() throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/properties.properties");
        properties.load(is);
        is.close();
        return properties;
    }

    //savet de nieuwe waardes voor welke kassabon moet gemaakt worden in de properties file (in dit geval headerbon)
    public void saveHeader() throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("HEADER", "TRUE");
        properties.setProperty("BEIDE", "FALSE");
        properties.setProperty("FOOTER", "FALSE");
        properties.store(out, null);
        out.close();
    }

    //savet de nieuwe waardes voor welke kassabon moet gemaakt worden in de properties file (in dit geval beide)
    public void saveBeide() throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("HEADER", "FALSE");
        properties.setProperty("BEIDE", "TRUE");
        properties.setProperty("FOOTER", "FALSE");
        properties.store(out, null);
        out.close();
    }

    //savet de nieuwe waardes voor welke kassabon moet gemaakt worden in de properties file (in dit geval footerbon)
    public void saveFooter() throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("HEADER", "FALSE");
        properties.setProperty("BEIDE", "FALSE");
        properties.setProperty("FOOTER", "TRUE");
        properties.store(out, null);
        out.close();
    }
}
