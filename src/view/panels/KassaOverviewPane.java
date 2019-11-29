package view.panels;

import database.ArtikelDbContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Artikel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 @Author Axel Hamelryck
 **/

public class KassaOverviewPane extends GridPane {
    private TableView<Artikel> table = new TableView<>();
    private ArtikelDbContext artikelDbContext;
    private ObservableList<Artikel> artikels;
    double uitkomst;

    public KassaOverviewPane(ArtikelDbContext artikelDbContext){
        this.artikelDbContext = artikelDbContext;
        artikels = FXCollections.observableArrayList(new ArrayList<Artikel>());

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        Label totaal = new Label();
        this.add(totaal,0,1,1,1);
        this.add(new Label("Add product:"), 1, 0, 1, 1);
        TextField artikelField = new TextField();
        this.add(artikelField,2,0,1,1);
        Button buttonAddArtikel = new Button("Add artikel");
        this.add(buttonAddArtikel,2,1,1,1 );

        buttonAddArtikel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                artikelDbContext.toStringTest();
//                 System.out.println(artikelDbContext.getArtikel(artikelField.getText()).toString());
                    try{
                        uitkomst += artikelDbContext.getArtikel(artikelField.getText()).getPrijs();
                        artikels.add(artikelDbContext.getArtikel(artikelField.getText()));
                        artikels.removeAll(Collections.singleton(null));
                        totaal.setText("Totaal: " + uitkomst);
                    }
                    catch(NullPointerException e){
                        displayErrorMessage("Niet bestaande code.");
                        artikelField.clear();
                    }
                refresh();
            }
        });
        this.getChildren().addAll(table);
        setTable();
    }

    public void displayErrorMessage(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }

    public void refresh(){
        table.refresh();
    }

    public void setTable(){
        table.setEditable(false);
        table.setItems(artikels);
        TableColumn omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Omschrijving"));
        omschrijvingColumn.setMinWidth(100);
        TableColumn prijsColumn = new TableColumn("Prijs");
        prijsColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Prijs"));
        prijsColumn.setMinWidth(100);
        table.getColumns().addAll(omschrijvingColumn, prijsColumn);
    }
}
