package view.panels;

import controller.KassaOverviewController;
import database.ArtikelDbContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import model.Artikel;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;
import model.ObserverPattern.Subject;
import java.util.*;

/**
 @Author Axel Hamelryck
 @Author Kasper Vanderhulst
 **/

public class KassaOverviewPane extends GridPane{
    private TableView<Artikel> table = new TableView<>();
    private Label totaal = new Label();
    private Button buttonAddArtikel = new Button("Add artikel");
    private KassaOverviewController kassaOverviewController;

    public KassaOverviewPane(KassaOverviewController kassaOverviewController){
        this.kassaOverviewController = kassaOverviewController;
        setGlobalEventHandler(this);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(totaal,0,1,1,1);
        this.add(new Label("Add product:"), 1, 0, 1, 1);
        TextField artikelField = new TextField();
        this.add(artikelField,2,0,1,1);

        this.add(buttonAddArtikel,2,1,1,1 );
        Button button2 = new Button("Remove artikel");
        button2.setOnAction(e ->{
            try{
                TableView.TableViewSelectionModel<Artikel> t = table.getSelectionModel();
                kassaOverviewController.handleDelete(kassaOverviewController.getArtikel(t.getSelectedItem().getCode()));
                refresh();
            }catch(NullPointerException | IllegalArgumentException ex){
                displayErrorMessage("Geen artikels geselecteerd");
            }
        });
        buttonAddArtikel.setOnAction(e -> {
            try{
                kassaOverviewController.addArtikel(kassaOverviewController.getArtikel(artikelField.getText()));
            }
            catch(NullPointerException | IllegalArgumentException eq){
                displayErrorMessage("Niet bestaande code.");
                artikelField.clear();
            }
            refresh();
            artikelField.clear();
        });
        Button buttonHold = new Button("Put on hold");
        buttonHold.setOnAction(e ->{
            kassaOverviewController.handleHold();
            refresh();
        });
        this.add(buttonHold,1,1,1,1);
        this.add(button2,3,1,1,1);
        this.getChildren().addAll(table);
        kassaOverviewController.uitkomst();
        totaal.setText("Totaal: $" + kassaOverviewController.getUitkomst() );
        setTable();
    }

    public void refresh(){
        table.getSelectionModel().clearSelection();
        kassaOverviewController.uitkomst();
        totaal.setText("Totaal: $" + kassaOverviewController.getUitkomst() );
        kassaOverviewController.notifyObserver(EventType.KLANTVIEW, kassaOverviewController.getArtikels());
        table.refresh();
    }

    public void setTable(){
        table.setEditable(false);
        table.setItems(kassaOverviewController.getArtikels());
        TableColumn omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Omschrijving"));
        omschrijvingColumn.setMinWidth(100);
        TableColumn prijsColumn = new TableColumn("Prijs");
        prijsColumn.setCellValueFactory(new PropertyValueFactory<Artikel,Double>("Prijs"));
        prijsColumn.setMinWidth(100);
        table.getColumns().addAll(omschrijvingColumn, prijsColumn);
    }

    //Code om een artikel toe te voegen als men op enter drukt (gooit enkel exception als men in het vakje zit)
    private void setGlobalEventHandler(Node root) {
        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                buttonAddArtikel.fire();
                ev.consume();
            }
        });
    }

    public void displayErrorMessage(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }
}
