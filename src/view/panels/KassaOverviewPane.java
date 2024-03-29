package view.panels;

import controller.KassaOverviewController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import model.Artikel;
import model.DomainException;
import model.ObserverPattern.EventType;

import java.io.IOException;
import java.util.*;

/**
 @author Axel Hamelryck
 @author Kasper Vanderhulst
 @author Noa Andries
 **/

public class KassaOverviewPane extends GridPane{
    private TableView<Artikel> table = new TableView<>();
    private Label totaal = new Label();
    private Button buttonAddArtikel = new Button("Add artikel");
    private KassaOverviewController kassaOverviewController;
    private Button buttonHold = new Button("Put on hold");

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
        Button buttonAfhandel = new Button("Afhandelen");
        this.add(buttonAddArtikel,2,1,1,1 );
        this.add(buttonAfhandel,2,2,1,1);
        buttonAfhandel.setOnAction(e -> {
            try {
                isEmpty();
                setAfhandelScreen();
                buttonHold.setVisible(true);
                refresh();
            } catch (DomainException | NullPointerException ex) {
                displayErrorMessage("Geen items in winkelkar.");
            }catch(IllegalArgumentException ex2){
                ex2.printStackTrace();
                displayErrorMessage(ex2.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Button button2 = new Button("Remove artikel");
        button2.setOnAction(e ->{
            try{
                TableView.TableViewSelectionModel<Artikel> t = table.getSelectionModel();
                kassaOverviewController.handleDelete(kassaOverviewController.getArtikel(t.getSelectedItem().getCode()));
                refresh();
            }catch(IllegalArgumentException| NullPointerException ex){
                displayErrorMessage("Geen item geselecteert");
            }
        });
        buttonAddArtikel.setOnAction(e -> {
            try{
                kassaOverviewController.addArtikel(kassaOverviewController.getArtikel(artikelField.getText()));
            }
            catch(NullPointerException | IllegalArgumentException eq){
                displayErrorMessage(eq.getMessage());
                artikelField.clear();
            }
            refresh();
            artikelField.clear();
        });

        buttonHold.setOnAction(e ->{
            try {
                isEmpty();
                kassaOverviewController.handleHold();
                buttonHold.setVisible(false);
                refresh();
            }
            catch(NullPointerException ex){
                displayErrorMessage("Geen items in winkelkar.");
            }
            catch (IllegalArgumentException il)
            {
                displayErrorMessage(il.getMessage());
            }
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

    private void isEmpty(){
        if(table.getItems().isEmpty()){
            throw new NullPointerException("Geen items in winkelkar");
        }
    }

    private void setAfhandelScreen() throws IOException {
        kassaOverviewController.handleAfhandel();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Afhandelen verkoop");
        alert.setContentText("De klant moet $" + kassaOverviewController.getUitkomstKorting()  + " betalen.");
        ButtonType okButton = new ButtonType("Betaald?", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Niet betaald", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("Cancel",ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton,cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == okButton){
            kassaOverviewController.verkoop();
        }
        else if(result.get()==cancelButton){
            kassaOverviewController.cancel();
        }

        }
    }

