package database;

import model.Artikel;
import model.DomainException;

import java.util.List;

public abstract class TekstLoadSaveTemplate {

    abstract List<Artikel> load() throws DomainException;
    abstract void save(List<Artikel> artikels) throws DomainException;
}
