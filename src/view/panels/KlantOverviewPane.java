package view.panels;

import controller.KlantOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Artikel;
import model.ObserverPattern.EventType;

import java.util.*;
import java.util.stream.Collectors;

/**
 @Author Axel Hamelryck
 **/

public class KlantOverviewPane extends GridPane {
    private TableView<Artikel> table = new TableView<Artikel>();
    private Label totaal = new Label();
    private KlantOverviewController klantOverviewController;


    public KlantOverviewPane(KlantOverviewController klantOverviewController){
        this.klantOverviewController = klantOverviewController;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(totaal,0,1,1,1);
        this.getChildren().addAll(table);
        setTable();
    }



    public void setTable(){
        table.setEditable(false);
        TableColumn omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Omschrijving"));
        omschrijvingColumn.setMinWidth(100);
        TableColumn prijsColumn = new TableColumn("Prijs");
        prijsColumn.setCellValueFactory(new PropertyValueFactory<Artikel,Double>("Prijs"));
        prijsColumn.setMinWidth(100);
        TableColumn aantalColumn = new TableColumn<Artikel, Integer>("Aantal");
        aantalColumn.setCellValueFactory(new PropertyValueFactory<Artikel,Integer>("Aantal"));
        aantalColumn.setMinWidth(100);
        table.getColumns().addAll(omschrijvingColumn, prijsColumn,aantalColumn);
    }

    public void updateTable(ObservableList<Artikel> artikels){
        totaal.setText("Totaal: $" + klantOverviewController.getUitkomst());
        klantOverviewController.updateTable(artikels);
        table.setItems(klantOverviewController.getArtikelsVerkoop());
        table.refresh();
    }



}
