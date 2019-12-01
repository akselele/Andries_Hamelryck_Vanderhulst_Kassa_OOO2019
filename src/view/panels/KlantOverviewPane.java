package view.panels;

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
    private ObservableList<Artikel> artikels;
    private double uitkomst;
    private double uitkomstHold;
    private ObservableList<Artikel> artikelsHold;
    private ObservableList<Artikel> artikelsVerkoop;


    public KlantOverviewPane(){
        artikels = FXCollections.observableArrayList(new ArrayList<Artikel>());
        artikelsHold = FXCollections.observableArrayList(new ArrayList<Artikel>());
        artikelsVerkoop = FXCollections.observableArrayList(new ArrayList<Artikel>());
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(totaal,0,1,1,1);
        this.getChildren().addAll(table);
        setTable();
    }

    public void refresh(){
        artikels.removeAll(Collections.singleton(null));
        Iterator<Artikel> i = artikels.iterator();
        for(Artikel artikel : new ArrayList<>(artikels)){
            if(artikel.getAantal() == 0){
                artikels.remove(artikel);
            }
        }
        uitkomst();
        totaal.setText("Totaal: $" + uitkomst);
        table.refresh();
    }

    public void uitkomst(){
        uitkomst = 0;
        for(Artikel artikel : artikelsVerkoop){
            uitkomst += artikel.getAantal() * artikel.getPrijs();
        }
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

    public void add(ObservableList<Artikel> artikels, String remove){
        try {
            for(Artikel artikel : artikels){
                artikel.setAantal(Collections.frequency(artikels,artikel));
            }
            List<Artikel> artikelstest = artikels.stream().distinct().collect(Collectors.toList());
            artikelsVerkoop.clear();
            artikelsVerkoop.addAll(artikelstest);
            table.setItems(artikelsVerkoop);
            if(remove.equalsIgnoreCase("hold")){
                List<Artikel> tmpList = new ArrayList<>(artikels);
                double tmpUitkomst;
                artikels.clear();
                artikels.addAll(artikelsHold);
                artikelsHold.clear();
                artikelsHold.addAll(tmpList);
                tmpUitkomst = uitkomst;
                uitkomst = uitkomstHold;
                uitkomstHold = tmpUitkomst;
            }
            refresh();
        }
        //Deze catch is leeg omdat in KassaOverviewPane al een nullpointerexception wordt gegooid, anders zijn er 2 warning screens.
        catch(NullPointerException e){

        }
    }



}
