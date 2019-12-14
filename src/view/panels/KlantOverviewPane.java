package view.panels;

import controller.KlantOverviewController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.Artikel;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 @Author Axel Hamelryck
 @Author Kasper Vanderhulst
 @Author Noa Andries
 **/

public class KlantOverviewPane extends GridPane implements Observer {
    private TableView<Pair<Artikel,Integer>> table = new TableView<>();
    private Label totaal = new Label();
    private Label totaalKorting = new Label();
    private KlantOverviewController klantOverviewController;
    private Label afhandel = new Label();


    public KlantOverviewPane(KlantOverviewController klantOverviewController){
        this.klantOverviewController = klantOverviewController;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(totaal,0,1,1,1);
        this.add(totaalKorting,0,3,1,1);
        this.add(afhandel,0,2,1,1);
        this.getChildren().addAll(table);
        setTable();
    }



    public void setTable(){
        table.setEditable(false);

        TableColumn<Pair<Artikel,Integer>,String> omschrijvingColumn = new TableColumn<>("Omschrijving");
        omschrijvingColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKey().getOmschrijving()));

        TableColumn<Pair<Artikel,Integer>,Number> prijsColumn = new TableColumn<>("Prijs");
        prijsColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getKey().getPrijs()));

        TableColumn<Pair<Artikel,Integer>,Number> aantalColumn = new TableColumn<>("Aantal");
        aantalColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getValue()));

        omschrijvingColumn.setMinWidth(100);
        prijsColumn.setMinWidth(100);
        aantalColumn.setMinWidth(100);
        table.getColumns().addAll(omschrijvingColumn, prijsColumn, aantalColumn);
    }

    public void update(ObservableList<Artikel> artikels){
        klantOverviewController.update(artikels);
        totaal.setText("Totaal: $" + klantOverviewController.getUitkomst());
        table.setItems(klantOverviewController.getArtikels());
        table.refresh();
        afhandel.setText("");
    }

    public void update(double uitkomst){
        DecimalFormat f = new DecimalFormat("##.00");
        totaalKorting.setText("Te betalen: $" + f.format(klantOverviewController.getUitkomstKorting()));
        afhandel.setText("Korting: $" +f.format(klantOverviewController.getUitkomst() - klantOverviewController.getUitkomstKorting()));
    }



}
