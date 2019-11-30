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
    double uitkomst;
    private Map<EventType, List<Observer>> observers;

    public KassaOverviewPane(ArtikelDbContext artikelDbContext){
        observers = new HashMap<>();
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
                        totaal.setText("Totaal: $" + uitkomst);

                    }
                    catch(NullPointerException e){
                        displayErrorMessage("Niet bestaande code.");
                        artikelField.clear();
                    }
                refresh(artikelDbContext.getArtikel(artikelField.getText()));
                artikelField.clear();
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

    public void refresh(Artikel artikel){
        table.refresh();
        notifyObserver(EventType.KLANTVIEW, artikel, uitkomst);
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
    public void notifyObserver(EventType e, Artikel a, double uitkomst) {
        for (Observer o:this.observers.get(e)) {
            o.update(a, uitkomst);
        }
    }
}
