package view;


import controller.KlantOverviewController;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Artikel;
import model.DomainException;
import model.ObserverPattern.EventType;
import view.panels.KlantOverviewPane;

/**
 @Author Axel Hamelryck
 @Author Kasper Vanderhulst
 **/

public class KlantMainPane extends BorderPane {
    private KlantOverviewPane klantOverviewPane;
    private KlantOverviewController klantOverviewController;

    public KlantMainPane() throws DomainException {
        klantOverviewController = new KlantOverviewController();
        TabPane tabPane = new TabPane();
        klantOverviewPane = new KlantOverviewPane(klantOverviewController);
        Tab kassaTab = new Tab("Kassa",klantOverviewPane);
        tabPane.getTabs().add(kassaTab);
        this.setCenter(tabPane);
    }

    public void add(ObservableList<Artikel> artikels){
        klantOverviewPane.update(artikels);
    }


}
