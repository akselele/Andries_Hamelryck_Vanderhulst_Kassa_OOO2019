package database;

import database.excel.ArtikelExcelLoadSaveStrategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.DomainException;

import java.util.*;

/**
 @Author Axel Hamelryck
 @Author Noa Andries
 **/

public class ArtikelDbInMemory implements ArtikelDbStrategy {
    private Map<String, Artikel> artikels = new HashMap<>();
    LoadSaveStrategy loadSaveStrategy;
    private ObservableList<Artikel> data;

    public ArtikelDbInMemory() throws DomainException{
        loadSaveStrategy = new ArtikelTekstLoadSave();
        load();
        data = FXCollections.observableArrayList(new ArrayList<Artikel>());
        data.addAll(artikels.values());
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy) throws DomainException {
        this.loadSaveStrategy = loadSaveStrategy;
        load();
    }

    @Override
    public Artikel getArtikel(String code){
        if(code.trim().isEmpty() || code.equalsIgnoreCase(null) || artikels.get(code) == null){
            throw new IllegalArgumentException("No code given");
        }
        return artikels.get(code);
    }


    //ObservableList returnen is nodig om het in een tableview te kunnen weergeven
    @Override
    public ObservableList<Artikel> getAll(){
        data.sort(Comparator.comparing(Artikel::getOmschrijving));
        return data;
    }

    //Niet nodig maar is handig voor debugging; returnt de hashMap
    @Override
    public Map<String,Artikel> getArtikels(){
        return artikels;
    }

    //Alles van de load van de lees klasse toevoegen aan de hashMap
    @Override
    public void load() throws DomainException {
        for(Artikel artikel: loadSaveStrategy.load()){
            add(artikel);
        }
    }


    //toevoegen aan de hashmap
    @Override
    public void add(Artikel artikel){
        if(artikel == null){
            throw new IllegalArgumentException("Artikel mag niet leeg zijn");
        }
        artikels.put(artikel.getCode(),artikel);
    }


    //hashmap returnen om te zien of het werkt
    public void toStringTest(){
        artikels.entrySet().forEach(entry->{
            System.out.println(entry.getValue());
        });
    }


    //Het saven van eventueel gewijzigde artikels, wat nog niet gebruikt wordt.
    //Het werkt wel als men het test via een ander uitvoerbestand
    @Override
    public void save() throws DomainException{
        loadSaveStrategy.save(new ArrayList<Artikel>(artikels.values()));
    }
}
