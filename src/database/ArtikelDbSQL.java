package database;

import javafx.collections.ObservableList;
import model.Artikel;
import model.DomainException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

/**
 @Author Kasper Vanderhulst
 **/

//Lege sql klasse voor uitbreiding

public class ArtikelDbSQL implements ArtikelDbStrategy {
    @Override
    public Artikel getArtikel(String code) {
        return null;
    }

    @Override
    public ObservableList<Artikel> getAll() {
        return null;
    }

    @Override
    public Map<String, Artikel> getArtikels() {
        return null;
    }

    @Override
    public void load() throws DomainException {

    }

    @Override
    public void add(Artikel artikel) {

    }

    @Override
    public void save(ArrayList<Artikel> artikels) throws DomainException {

    }

    @Override
    public void toStringTest() {

    }

    @Override
    public void propertiesSave(String property) throws FileNotFoundException {

    }

    @Override
    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy, String property) throws FileNotFoundException {

    }
}
