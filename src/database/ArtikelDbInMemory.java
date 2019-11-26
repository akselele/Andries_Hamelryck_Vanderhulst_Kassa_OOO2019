package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.DomainException;

import java.util.*;

public class ArtikelDbInMemory {
    private Map<String, Artikel> artikels = new HashMap<>();
    ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave();
    private ObservableList<Artikel> data;

    public ArtikelDbInMemory() throws DomainException{
        load();
        data = FXCollections.observableArrayList(new ArrayList<Artikel>());
        data.addAll(artikels.values());
    }

    public Artikel get(String code){
        if(code == null){
            throw new IllegalArgumentException("No code given");
        }
        return artikels.get(code);
    }

    public ObservableList<Artikel> getAll(){
        data.sort(Comparator.comparing(Artikel::getOmschrijving));
        return data;
    }

    public Map<String,Artikel> getArtikels(){
        return artikels;
    }

    public void load() throws DomainException {
        for(Artikel artikel: artikelTekstLoadSave.load()){
            add(artikel);
        }
    }

    public void add(Artikel artikel){
        if(artikel == null){
            throw new IllegalArgumentException("Artikel mag niet leeg zijn");
        }
        artikels.put(artikel.getCode(),artikel);
    }

    public void toStringTest(){
        artikels.entrySet().forEach(entry->{
            System.out.println(entry.getValue());
        });
    }

    public void save() throws DomainException{
        artikelTekstLoadSave.save(new ArrayList<Artikel>(artikels.values()));
    }
}
