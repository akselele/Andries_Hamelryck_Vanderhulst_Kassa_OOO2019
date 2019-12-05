package view;


import controller.KassaOverviewController;
import controller.ProductOverviewController;
import database.ArtikelDbContext;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.DomainException;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;
import view.panels.KassaOverviewPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 @Author Axel Hamelryck
 **/

public class KassaMainPane extends BorderPane {
    private Map<EventType, List<Observer>> observers;
    private ArtikelDbContext artikelDbContext = new ArtikelDbContext();
    private KassaOverviewController kassaOverviewController = new KassaOverviewController(artikelDbContext);
    private KassaOverviewPane kassaOverviewPane = new KassaOverviewPane(kassaOverviewController);
    private ProductOverviewController productOverviewController = new ProductOverviewController(artikelDbContext);
    private SettingsPane settingsPane = new SettingsPane();


	public KassaMainPane() throws DomainException {
	    observers = new HashMap<>();

	    TabPane tabPane = new TabPane();
        Tab kassaTab = new Tab("Kassa",kassaOverviewPane);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(productOverviewController);
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen", settingsPane);
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}

    public void registerObserver(EventType e, Observer o) {
	    kassaOverviewController.registerObserver(e,o);
    }




}
