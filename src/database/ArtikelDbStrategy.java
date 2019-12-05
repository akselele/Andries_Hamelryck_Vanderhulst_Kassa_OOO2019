package database;

import javafx.collections.ObservableList;
import model.Artikel;
import model.DomainException;

import java.util.Map;
/**
 @Author Kasper Vanderhulst
 @Author Noa Andries
 **/

public interface ArtikelDbStrategy {
    Artikel getArtikel(String code);

    //ObservableList returnen is nodig om het in een tableview te kunnen weergeven
    ObservableList<Artikel> getAll();

    //Niet nodig maar is handig voor debugging
    Map<String,Artikel> getArtikels();

    //Alles van de load van de lees klasse toevoegen aan de hashMap
    void load() throws DomainException;

    //toevoegen aan de hashmap
    void add(Artikel artikel);

    //Het saven van eventueel gewijzigde artikels, wat nog niet gebruikt wordt.
    //Het werkt wel als men het test via een ander uitvoerbestand
    void save() throws DomainException;

    void toStringTest();
}
