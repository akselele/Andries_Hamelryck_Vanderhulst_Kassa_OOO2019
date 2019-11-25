package database;

import model.Artikel;
import model.DomainException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtikelDbInMemory {
    private Map<String, Artikel> artikels = new HashMap<>();
    ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave();

    public ArtikelDbInMemory(){
        Artikel a1 = new Artikel("1", "DVD Frozen 2", "DVD", 9.99, 5);
    }

    public Artikel get(String code){
        if(code == null){
            throw new IllegalArgumentException("No code given");
        }
        return artikels.get(code);
    }

    public List<Artikel> getAll(){
        return new ArrayList<Artikel>(artikels.values());
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
