
package view;


import controller.KassaState.KassaOverviewController;
import controller.LogController;
import controller.ProductOverviewController;
import database.ArtikelDbContext;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;
import view.panels.KassaOverviewPane;
import view.panels.LogPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Axel Hamelryck
 **/

public class KassaMainPane extends BorderPane {
    private Map<EventType, List<Observer>> observers;
    private ArtikelDbContext artikelDbContext = new ArtikelDbContext();
    private LogController logController = new LogController();
    private LogPane logPane = new LogPane(logController);
    private KassaOverviewController kassaOverviewController = new KassaOverviewController(artikelDbContext, logPane);
    private KassaOverviewPane kassaOverviewPane = new KassaOverviewPane(kassaOverviewController);
    private ProductOverviewController productOverviewController = new ProductOverviewController(artikelDbContext);
    private SettingsPane settingsPane = new SettingsPane();


    public KassaMainPane() throws IOException {
        observers = new HashMap<>();
        TabPane tabPane = new TabPane();
        Tab kassaTab = new Tab("Kassa", kassaOverviewPane);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(productOverviewController);
        kassaOverviewController.registerObserver(EventType.KASSAVIEW, productOverviewPane);
        Tab artikelTab = new Tab("Artikelen", productOverviewPane);
        Tab instellingTab = new Tab("Instellingen", settingsPane);
        Tab logTab = new Tab("Log", logPane);
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
        this.setCenter(tabPane);
    }

    public void registerObserver(EventType e, Observer o) {
        kassaOverviewController.registerObserver(e, o);
    }


}