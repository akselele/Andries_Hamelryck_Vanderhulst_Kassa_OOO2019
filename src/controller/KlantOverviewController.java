package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.ObserverPattern.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class KlantOverviewController {
    private ObservableList<Artikel> artikels;
    private double uitkomst;
    private ObservableList<Artikel> artikelsVerkoop;

    public KlantOverviewController(){
        artikels = FXCollections.observableArrayList(new ArrayList<Artikel>());
        artikelsVerkoop = FXCollections.observableArrayList(new ArrayList<Artikel>());
    }

    public void refresh(){
        artikels.removeAll(Collections.singleton(null));
        Iterator<Artikel> i = artikels.iterator();
        for(Artikel artikel : new ArrayList<>(artikels)){
            if(artikel.getAantal() == 0){
                artikels.remove(artikel);
            }
        }
        uitkomst();
    }

    public void uitkomst(){
        uitkomst = 0;
        for(Artikel artikel : artikelsVerkoop){
            uitkomst += artikel.getAantal() * artikel.getPrijs();
        }
    }

    public ObservableList<Artikel> getArtikelsVerkoop(){
        return artikelsVerkoop;
    }

    public double getUitkomst() {
        return uitkomst;
    }

    public void update(ObservableList<Artikel> artikels){
        try {
            for(Artikel artikel : artikels){
                artikel.setAantal(Collections.frequency(artikels,artikel));
            }
            List<Artikel> artikelstest = artikels.stream().distinct().collect(Collectors.toList());
            artikelsVerkoop.clear();
            artikelsVerkoop.addAll(artikelstest);
            refresh();
        }
        //Deze catch is leeg omdat in KassaOverviewPane al een nullpointerexception wordt gegooid, anders zijn er 2 warning screens.
        catch(NullPointerException e){

        }
    }
}
