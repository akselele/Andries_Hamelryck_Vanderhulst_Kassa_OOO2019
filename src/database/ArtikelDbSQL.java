package database;

import javafx.collections.ObservableList;
import model.Artikel;
import model.DomainException;

import java.util.Map;

//Lege sql klasse voor uitbreiding

public class ArtikelDbSQL implements ArtikelDbStrategy {
    @Override
    public Artikel get(String code) {
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
    public void save() throws DomainException {

    }

    @Override
    public void toStringTest() {

    }
}
