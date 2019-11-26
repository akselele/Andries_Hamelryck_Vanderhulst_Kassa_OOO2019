package view;


import database.ArtikelDbInMemory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.DomainException;
import view.panels.ProductOverviewPane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane() throws DomainException {
        ArtikelDbInMemory artikelDbInMemory = new ArtikelDbInMemory();
	    TabPane tabPane = new TabPane(); 	    
        Tab kassaTab = new Tab("Kassa");
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(artikelDbInMemory);
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
