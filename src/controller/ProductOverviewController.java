package controller;

import database.ArtikelDbContext;
import javafx.collections.ObservableList;
import model.Artikel;
import model.ObserverPattern.Observer;

public class ProductOverviewController {
    private ArtikelDbContext artikelDbContext;

    public ProductOverviewController(ArtikelDbContext artikelDbContext){
        this.artikelDbContext = artikelDbContext;
    }

    public ObservableList<Artikel> getAll(){
        return artikelDbContext.getAll();
    }

}
