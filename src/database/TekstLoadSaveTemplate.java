package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 @Author Axel Hamelryck
 **/

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {

    public abstract List<Artikel> load() throws FileNotFoundException;
    public abstract void save(List<Artikel> artikels) throws IOException;
}
