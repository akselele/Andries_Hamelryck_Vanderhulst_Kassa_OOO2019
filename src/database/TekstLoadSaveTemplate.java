package database;

import model.Artikel;
import model.DomainException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 @author Axel Hamelryck
 **/

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {

    public abstract List<Artikel> load() throws DomainException;
    public abstract void save(List<Artikel> artikels) throws DomainException;
}
