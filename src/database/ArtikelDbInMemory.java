package database;

import database.excel.ArtikelExcelLoadSaveStrategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.DomainException;

import java.io.*;
import java.util.*;

/**
 @Author Axel Hamelryck
 @Author Noa Andries
 **/

public class ArtikelDbInMemory implements ArtikelDbStrategy {
    private Map<String, Artikel> artikels = new HashMap<>();
    LoadSaveStrategy loadSaveStrategy;
    private ObservableList<Artikel> data;

    public ArtikelDbInMemory() throws IOException {
        propertiesOpen();
        load();
        data = FXCollections.observableArrayList(new ArrayList<Artikel>());
        data.addAll(artikels.values());
    }

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy, String property) throws IOException {
        this.loadSaveStrategy = loadSaveStrategy;
        propertiesSave(property);
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
        artikels.clear();
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

    public void propertiesOpen() throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/properties.properties");
        properties.load(is);
        if(properties.getProperty("type").equalsIgnoreCase("text")){
            ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave();
            setLoadSaveStrategy(artikelTekstLoadSave,"text");
        }
        else if(properties.getProperty("type").equalsIgnoreCase("excel")){
            ArtikelExcelLoadSaveStrategy artikelExcelLoadSaveStrategy = new ArtikelExcelLoadSaveStrategy();
            setLoadSaveStrategy(artikelExcelLoadSaveStrategy, "excel");
        }
    }

    public void propertiesSave(String property) throws IOException {
        FileInputStream in = new FileInputStream("src/bestanden/properties.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/bestanden/properties.properties");
        properties.setProperty("type", property);
        properties.store(out, null);
        out.close();
        properties.setProperty("type",property);

    }
}
