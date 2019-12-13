package model.Kassabonnen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KassabonProperties {
    public  static Properties load() throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/properties.properties");
        properties.load(is);
        is.close();
        return properties;
    }

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
