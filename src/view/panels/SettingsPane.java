package view.panels;

import database.ArtikelDbContext;
import database.ArtikelDbInMemory;
import database.LoadSaveFactory;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.DomainException;

import java.io.FileNotFoundException;
import java.io.IOException;



public class SettingsPane extends GridPane {
    ArtikelDbContext artikelDbContext = new ArtikelDbContext();
    LoadSaveFactory loadSaveFactory = new LoadSaveFactory();



    public SettingsPane() throws IOException {
        HBox vBox = new HBox();
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);


       String[] vertaalLijst = artikelDbContext.getStrategiesList();

        for(String s : vertaalLijst){
            Button b = new Button(s);
            vBox.getChildren().add(b);
            System.out.println(s);

            b.setOnAction( event ->{
                try {
                    artikelDbContext.setLoadSaveStrategy(loadSaveFactory.getStrategy(s),s.toLowerCase());

                }catch (IOException e){
                    displayErrorMessage("Fout bij het laden.");
                }
            });

        }

        this.getChildren().add(vBox);
    }

    public void displayErrorMessage(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }
}
