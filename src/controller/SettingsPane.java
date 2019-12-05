package controller;

import database.ArtikelDbContext;
import database.ArtikelDbInMemory;
import database.LoadSaveFactory;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.DomainException;

public class SettingsPane extends GridPane {
    ArtikelDbInMemory artikelDbInMemory = new ArtikelDbInMemory();
    LoadSaveFactory loadSaveFactory = new LoadSaveFactory();



    public SettingsPane() throws DomainException {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        Button button1 = new Button("Excel");
        Button button2 = new Button("Text");
        button1.setOnAction( event ->
        {
            try {
                artikelDbInMemory.setLoadSaveStrategy(loadSaveFactory.getStrategy("EXCEL"));
            } catch (DomainException e) {
                e.printStackTrace();
            }


        });
        button2.setOnAction( event ->
        {
            try {
                artikelDbInMemory.setLoadSaveStrategy(loadSaveFactory.getStrategy("TEXT"));
            } catch (DomainException e) {
                e.printStackTrace();
            }


        });
        this.add(button1, 2,1,1,1 );
        this.add(button2, 3,1,1,1 );
        this.getChildren().addAll();
    }
}
