package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.Artikel;
import model.ObserverPattern.Observer;
import model.WinkelMandje;
import model.kortingen.DrempelKorting;
import model.kortingen.GroepKorting;
import model.kortingen.KortingContext;
import model.kortingen.KortingStrategy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Kasper Vanderhulst
 * @author Noa Andries
 **/

public class KlantOverviewController {
    private ObservableMap<Artikel, Integer> artikelsMap;
    private double uitkomst;

    KortingContext kortingContext = new KortingContext();

    public KlantOverviewController() {
        artikelsMap = FXCollections.observableMap(new TreeMap<>(Comparator.comparing(o -> o.getOmschrijving().toLowerCase())));
    }

    public void refresh() {
        artikelsMap.keySet().removeAll(Collections.singleton(null));
        Iterator<Artikel> i = artikelsMap.keySet().iterator();
        for (Artikel artikel : artikelsMap.keySet()) {
            if (artikelsMap.get(artikel) == 0) {
                artikelsMap.remove(artikel);
            }
        }
        uitkomst();
    }

    public void uitkomst() {
        uitkomst = 0;
        for (Artikel artikel : artikelsMap.keySet()) {
            uitkomst += artikelsMap.get(artikel) * artikel.getPrijs();
        }
    }



    public ObservableList<Pair<Artikel, Integer>> getArtikels() {
        return toPairList();
    }

    public double getUitkomst() {
       return uitkomst;


    }
    public double getUitkomstKorting() {
        WinkelMandje mandje = new WinkelMandje(artikelsMap);
        return kortingContext.getTotaleKorting(mandje);
    }

    public void update(ObservableList<Artikel> artikels) {
        try {
            artikelsMap.clear();
            int ammount = 1;
            for (Artikel artikel : artikels) {
                ammount = Collections.frequency(artikels, artikel);
                artikelsMap.put(artikel, ammount);
            }

            ObservableList<Pair<Artikel, Integer>> artikelList = toPairList();
            refresh();
        }
        //Deze catch is leeg omdat in KassaOverviewPane al een nullpointerexception wordt gegooid, anders zijn er 2 warning screens.
        catch (NullPointerException e) {
        }
    }

//verandert de map naar een lijst van pairs van artikel en aantal voor handiger gebruik in de ui
    private ObservableList<Pair<Artikel, Integer>> toPairList() {
        ObservableList<Pair<Artikel, Integer>> list = FXCollections.observableArrayList();
        for (Artikel artikel : artikelsMap.keySet()) {
            list.add(new Pair<>(artikel, artikelsMap.get(artikel)));
        }
        return list;
    }

}



