package database.excel;

import database.LoadSaveStrategy;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Artikel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 @author Noa Andries
 **/

public class ArtikelExcelLoadSaveStrategy  implements LoadSaveStrategy {
    private ExcelPlugin excelPlugin = new ExcelPlugin();
    private File file = new File("src/bestanden/artikel.xls");


    public ArtikelExcelLoadSaveStrategy(){}


    @Override
    public List<Artikel> load() {
        ArrayList artikels = new ArrayList();
        try {
            for (ArrayList<String> i : excelPlugin.read(file))
            {
                Artikel artikel = new Artikel(i.get(0), i.get(1), i.get(2), Double.parseDouble(i.get(3)), Integer.parseInt(i.get(4)));
                artikels.add(artikel);
            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
        return artikels;

    }

    @Override
    public void save(List<Artikel> artikels) {
        ArrayList teschrijven = new ArrayList();
        for (Artikel a : artikels)
        {
            ArrayList current = new ArrayList();
            current.add(a.getCode());
            current.add(a.getOmschrijving());
            current.add(a.getArtikelgroep());
            current.add(String.valueOf(a.getPrijs()));
            current.add(String.valueOf(a.getVoorraad()));
            teschrijven.add(current);
        }
        try {
            excelPlugin.write(file, teschrijven);
        } catch (BiffException | IOException | WriteException e) {
            e.printStackTrace();
        }

    }
}
