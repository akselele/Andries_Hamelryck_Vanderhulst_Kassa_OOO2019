package view.panels;

import controller.ProductOverviewController;
import database.ArtikelDbContext;
import database.ArtikelDbInMemory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Artikel;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;
import model.ObserverPattern.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 @Author Axel Hamelryck
 **/


public class ProductOverviewPane extends GridPane {
	private TableView<Artikel> table = new TableView<>();
	private ProductOverviewController productOverviewController;

	public ProductOverviewPane(ProductOverviewController productOverviewController) {
		this.productOverviewController = productOverviewController;
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
		this.add(new Label("Products:"), 0, 0, 1, 1);

		table.setEditable(false);
		table.setItems(productOverviewController.getAll());


		//Kolommen toevoegen + alle artikels inladen
		TableColumn<Artikel, String> codeColumn = new TableColumn<Artikel,String>("Code");
		codeColumn.setMinWidth(100);
		codeColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Code"));
		TableColumn omschrijvingColumn = new TableColumn("Omschrijving");
		omschrijvingColumn.setMinWidth(100);
		omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Omschrijving"));
		TableColumn artikelgroepColumn = new TableColumn("Artikelgroep");
		artikelgroepColumn.setMinWidth(100);
		artikelgroepColumn.setCellValueFactory(new PropertyValueFactory<Artikel,String>("Artikelgroep"));
		TableColumn prijsColumn = new TableColumn("Prijs");
		prijsColumn.setMinWidth(100);
		prijsColumn.setCellValueFactory(new PropertyValueFactory<Artikel,Double>("Prijs"));
		TableColumn voorraadColumn = new TableColumn("Voorraad");
		voorraadColumn.setMinWidth(100);
		voorraadColumn.setCellValueFactory(new PropertyValueFactory<Artikel,Integer>("Voorraad"));

		table.getColumns().addAll(codeColumn,omschrijvingColumn,artikelgroepColumn,prijsColumn,voorraadColumn);

		this.getChildren().addAll(table);
	}
}
