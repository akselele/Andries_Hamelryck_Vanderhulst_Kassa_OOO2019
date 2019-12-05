package view;


import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Artikel;
import model.DomainException;
import controller.KlantOverviewPane;

/**
 @Author Axel Hamelryck
 **/

public class KlantMainPane extends BorderPane {
    private KlantOverviewPane klantOverviewPane;

    public KlantMainPane() throws DomainException {
        TabPane tabPane = new TabPane();
        klantOverviewPane = new KlantOverviewPane();
        Tab kassaTab = new Tab("Kassa",klantOverviewPane);
        tabPane.getTabs().add(kassaTab);
        this.setCenter(tabPane);
    }

    public void add(ObservableList<Artikel> artikels){
        klantOverviewPane.add(artikels);
    }


}
