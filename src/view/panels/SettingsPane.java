package view.panels;

import database.ArtikelDbContext;
import database.LoadSaveFactory;
import javafx.scene.layout.GridPane;
import model.DomainException;

public class SettingsPane extends GridPane {
    ArtikelDbContext context = new ArtikelDbContext();
    LoadSaveFactory loadSaveFactory = new LoadSaveFactory();



    public SettingsPane() throws DomainException {
    }
}
