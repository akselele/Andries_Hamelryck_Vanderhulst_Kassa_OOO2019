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
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;
import model.ObserverPattern.Subject;

import java.util.*;

/**
 @Author Axel Hamelryck
 **/

public class KlantOverviewPane extends GridPane {
    private TableView<Artikel> table = new TableView<>();
    private ObservableList<Artikel> artikels;
    Label totaal = new Label();


    public KlantOverviewPane(){
        artikels = FXCollections.observableArrayList(new ArrayList<Artikel>());

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(totaal,0,1,1,1);


        this.getChildren().addAll(table);
        setTable();
    }

    //TODO Een artikel dat meerdere keren wordt toegevoegd moet in een aantal kolom komen te staan en niet meerdere keren worden toegevoegd
    public void refresh(double uitkomst){
        table.refresh();
        artikels.removeAll(Collections.singleton(null));
        totaal.setText("Totaal: $" + uitkomst);
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
        TableColumn aantalColumn = new TableColumn("Aantal");
        aantalColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Aantal"));
        aantalColumn.setMinWidth(100);
        table.getColumns().addAll(omschrijvingColumn, prijsColumn);
    }

    public void add(Artikel artikel, double uitkomst){
        artikels.add(artikel);
        refresh(uitkomst);
    }
}
