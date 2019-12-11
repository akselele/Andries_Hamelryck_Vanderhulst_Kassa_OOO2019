package view.panels;

import database.ArtikelDbContext;
import database.ArtikelDbInMemory;
import database.LoadSaveFactory;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.DomainException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingsPane extends GridPane {
    ArtikelDbContext artikelDbInMemory = new ArtikelDbContext();
    LoadSaveFactory loadSaveFactory = new LoadSaveFactory();



    public SettingsPane() throws IOException {
        VBox vBox = new VBox();
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        ArtikelDbContext artikelDbContext = new ArtikelDbContext();

        String[] vertaalLijst = artikelDbContext.getStrategiesList();

        for(String s : vertaalLijst){
            Button b = new Button(s);
            vBox.getChildren().add(b);
        }

        Button button1 = new Button("Excel");
        Button button2 = new Button("Text");
        button1.setOnAction( event ->
        {
            try {
                artikelDbInMemory.setLoadSaveStrategy(loadSaveFactory.getStrategy("EXCEL"), "excel");
            } catch (IOException e) {
                displayErrorMessage("Fout bij het laden.");
            }
        });
        button2.setOnAction( event ->
        {
            try {
                artikelDbInMemory.setLoadSaveStrategy(loadSaveFactory.getStrategy("TEXT"),"text");
            } catch (IOException e) {
                displayErrorMessage("Fout bij het laden.");
            }
        });
        this.add(button1, 2,1,1,1 );
        this.add(button2, 3,1,1,1 );
        this.getChildren().addAll();
        this.getChildren().add(vBox);
    }

    public void displayErrorMessage(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }
}
