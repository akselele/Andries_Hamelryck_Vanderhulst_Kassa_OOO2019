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
import model.kortingen.KortingContext;
import model.kortingen.KortingFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author Kasper Vanderhulst
 * @Author Noa Andries
 **/


public class SettingsPane extends GridPane {
    ArtikelDbContext artikelDbContext = new ArtikelDbContext();
    LoadSaveFactory loadSaveFactory = new LoadSaveFactory();
    KortingContext kortingContext = new KortingContext();
    KortingFactory kortingFactory = new KortingFactory();


    public SettingsPane() throws IOException {
        VBox vBox = new VBox();
        HBox hBoxloadsave = new HBox();
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);


        String[] vertaalLijst = artikelDbContext.getStrategiesList();

        for (String s : vertaalLijst) {
            Button b = new Button(s);
            hBoxloadsave.getChildren().add(b);

            b.setOnAction(event -> {
                try {
                    artikelDbContext.setLoadSaveStrategy(loadSaveFactory.getStrategy(s), s.toLowerCase());

                } catch (IOException e) {
                    displayErrorMessage("Fout bij het laden.");
                }
            });

        }

        vBox.getChildren().add(hBoxloadsave);


        HBox hboxKorting = new HBox();
        String[] kortingLijst = new KortingContext().getKortingList();

        for (String s : kortingLijst) {
            Button b = new Button(s);
            hboxKorting.getChildren().add(b);

            b.setOnAction(event -> {
                try {
                    System.out.println(s);
                    kortingContext.setKortingStrategyProperties(kortingFactory.createKorting(s), 10, "5");
                } catch (Exception e) {
                    displayErrorMessage("Fout bij het bepalen van de korting");
                }
            });
        }
        vBox.getChildren().add(hboxKorting);

        this.getChildren().add(vBox);


    }


    public void displayErrorMessage(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }
}
