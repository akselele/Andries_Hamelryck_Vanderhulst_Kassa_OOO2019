package model.kortingen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/
public class KassaProperties {
    public void saveDrempelwaarde(boolean type, int korting, int drempelwaarde) throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("DrempelKorting", String.valueOf(type));
        properties.setProperty("Drempelkorting", String.valueOf(korting));
        properties.setProperty("Drempelwaarde", String.valueOf(drempelwaarde));
        properties.store(out, null);
        out.close();
//        properties.setProperty("drempelwaarde", String.valueOf(type));
    }

}
