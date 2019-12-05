package database;

/**
 * @Author Axel Hamelryck
 * @Author Kasper Vanderhulst
 **/

import com.sun.javafx.collections.MappingChange;
import javafx.collections.ObservableList;
import model.Artikel;
import model.DomainException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArtikelDbContext {
    private ArtikelDbStrategy artikelDbStrategy;

    public ArtikelDbContext() throws DomainException {
        artikelDbStrategy = new ArtikelDbInMemory();
    }

    public Artikel getArtikel(String code) {
        return artikelDbStrategy.getArtikel(code);
    }

    public ObservableList<Artikel> getAll() {
        return artikelDbStrategy.getAll();
    }

    public Map<String, Artikel> getArtikels() {
        return artikelDbStrategy.getArtikels();
    }

    public void load() throws DomainException {
        artikelDbStrategy.load();
    }

    public void add(Artikel artikel) {
        artikelDbStrategy.add(artikel);
    }

    public void save() throws DomainException {
        artikelDbStrategy.save();
    }

    public void toStringTest() {
        artikelDbStrategy.toStringTest();
    }

    public String[] getStrategiesList(){
        List<String> strategiesList = new ArrayList<>();
        for(LoadSaveEnum loadSaveEnum : LoadSaveEnum.values()){
            strategiesList.add(loadSaveEnum.toString());
        }
        String[] strategiesListStr = new String[strategiesList.size()];
        strategiesListStr = strategiesList.toArray(strategiesListStr);
        return strategiesListStr;
    }
}
