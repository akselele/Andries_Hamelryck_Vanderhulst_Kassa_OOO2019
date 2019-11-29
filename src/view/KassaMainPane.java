package view;


import database.ArtikelDbContext;
import database.ArtikelDbInMemory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.DomainException;
import view.panels.KassaOverviewPane;
import view.panels.ProductOverviewPane;

/**
 @Author Axel Hamelryck
 **/

public class KassaMainPane extends BorderPane {
	public KassaMainPane() throws DomainException {
        ArtikelDbContext artikelDbContext = new ArtikelDbContext();
	    TabPane tabPane = new TabPane();
        KassaOverviewPane kassaOverviewPane = new KassaOverviewPane(artikelDbContext);
        Tab kassaTab = new Tab("Kassa",kassaOverviewPane);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(artikelDbContext);
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
