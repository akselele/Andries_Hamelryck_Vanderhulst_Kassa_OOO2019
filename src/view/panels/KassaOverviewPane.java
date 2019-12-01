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

import javax.swing.tree.ExpandVetoException;
import java.util.*;

/**
 @Author Axel Hamelryck
 **/

public class KassaOverviewPane extends GridPane implements Subject{
    private TableView<Artikel> table = new TableView<>();
    private ArtikelDbContext artikelDbContext;
    private ObservableList<Artikel> artikels;
    private ObservableList<Artikel> artikelsHold;
    private double uitkomst;
    private double uitkomstHold;
    private Map<EventType, List<Observer>> observers;
    Label totaal = new Label();

    public KassaOverviewPane(ArtikelDbContext artikelDbContext){
        observers = new HashMap<>();
        this.artikelDbContext = artikelDbContext;
        artikels = FXCollections.observableArrayList(new ArrayList<Artikel>());
        artikelsHold = FXCollections.observableArrayList(new ArrayList<Artikel>());

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(totaal,0,1,1,1);
        this.add(new Label("Add product:"), 1, 0, 1, 1);
        TextField artikelField = new TextField();
        this.add(artikelField,2,0,1,1);
        Button buttonAddArtikel = new Button("Add artikel");
        this.add(buttonAddArtikel,2,1,1,1 );
        Button button2 = new Button("Remove artikel");
        button2.setOnAction(new deleteArtikelHandler());
        buttonAddArtikel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    try{
                        Artikel artikel = artikelDbContext.getArtikel(artikelField.getText());
                        artikels.add(artikel);
                        artikels.removeAll(Collections.singleton(null));
                    }
                    catch(NullPointerException | IllegalArgumentException e){
                        displayErrorMessage("Niet bestaande code.");
                        artikelField.clear();
                    }
                refresh("notremove");
                artikelField.clear();
            }
        });
        Button buttonHold = new Button("Put on hold");
        buttonHold.setOnAction(new putOnHold());
        this.add(buttonHold,1,1,1,1);
        this.add(button2,3,1,1,1);
        this.getChildren().addAll(table);
        uitkomst();
        totaal.setText("Totaal: $" + uitkomst );
        setTable();
    }

    public void refresh(String remove){
        table.getSelectionModel().clearSelection();
        uitkomst();
        totaal.setText("Totaal: $" + uitkomst );
        notifyObserver(EventType.KLANTVIEW, artikels, remove);
        table.refresh();
    }

    public void uitkomst(){
        uitkomst = 0;
        for(Artikel artikel : artikels){
            uitkomst += artikel.getPrijs();
        }
    }

    public void displayErrorMessage(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }

    public void setTable(){
        table.setEditable(false);
        table.setItems(artikels);
        TableColumn omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Omschrijving"));
        omschrijvingColumn.setMinWidth(100);
        TableColumn prijsColumn = new TableColumn("Prijs");
        prijsColumn.setCellValueFactory(new PropertyValueFactory<Artikel,Double>("Prijs"));
        prijsColumn.setMinWidth(100);
        table.getColumns().addAll(omschrijvingColumn, prijsColumn);
    }

    @Override
    public void registerObserver(EventType e, Observer o) {
        if (observers.get(e) == null){
            List<Observer> observers = new ArrayList<>();
            observers.add(o);
            this.observers.put(e,observers);
        }else{
            List<Observer> observers = this.observers.get(e);
            observers.add(o);
        }
    }

    @Override
    public void unregisterObserver(EventType e, Observer o) {
        this.observers.get(e).remove(o);
    }

    @Override
    public void notifyObserver(EventType e, ObservableList<Artikel> artikels, String remove) {
            for (Observer o:this.observers.get(e)) {
                o.update(artikels, remove);
            }
    }

    class deleteArtikelHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try{
                TableView.TableViewSelectionModel<Artikel> t = table.getSelectionModel();
                Artikel artikel = artikelDbContext.getArtikel(t.getSelectedItem().getCode());
                artikels.remove(artikel);
                refresh("remove");
            }
            catch(NullPointerException e){
                displayErrorMessage("Geen artikels geselecteerd");
            }

        }
    }

    class putOnHold implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            List<Artikel> tmpList = new ArrayList<>(artikels);
            double tmpUitkomst;
            artikels.clear();
            artikels.addAll(artikelsHold);
            artikelsHold.clear();
            artikelsHold.addAll(tmpList);
            tmpUitkomst = uitkomst;
            uitkomst = uitkomstHold;
            uitkomstHold = tmpUitkomst;
            refresh("asd");
        }
    }
}
