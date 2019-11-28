package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface LoadSaveStrategy {

    List<Artikel> load() throws FileNotFoundException;

    void save(List<Artikel> artikels) throws IOException;
}
