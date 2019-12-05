package controller;

import database.ArtikelDbContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import model.Artikel;
import model.ObserverPattern.EventType;
import model.ObserverPattern.Observer;
import model.ObserverPattern.Subject;

import java.util.*;

public class KassaOverviewController implements Subject {
    private ArtikelDbContext artikelDbContext;
    private ObservableList<Artikel> artikels;
    private ObservableList<Artikel> artikelsHold;
    private double uitkomst;
    private double uitkomstHold;
    private Map<EventType, List<Observer>> observers;

    public KassaOverviewController(ArtikelDbContext artikelDbContext){
        observers = new HashMap<>();
        this.artikelDbContext = artikelDbContext;
        artikels = FXCollections.observableArrayList(new ArrayList<Artikel>());
        artikelsHold = FXCollections.observableArrayList(new ArrayList<Artikel>());
    }

    public void addArtikel(Artikel artikel){
            artikels.add(artikel);
            artikels.removeAll(Collections.singleton(null));
            notifyObserver(EventType.KLANTVIEW, artikels);
    }

    public Artikel getArtikel(String code){
        return artikelDbContext.getArtikel(code);
    }

    public ObservableList<Artikel> getArtikels() {
        return artikels;
    }


    @Override
    public void registerObserver(EventType e, Observer o) {
        if (observers.get(e) == null){
            List<Observer> observers = new ArrayList<>();
            observers.add(o);
            this.observers.put(e,observers);
        }else{
            List<Observer> observers = this.observers.get(e);
            observers.add(o);
        }
    }

    @Override
    public void unregisterObserver(EventType e, Observer o) {
        this.observers.get(e).remove(o);
    }

    @Override
    public void notifyObserver(EventType e, ObservableList<Artikel> artikels) {
        for (Observer o:this.observers.get(e)) {
            o.update(artikels);
        }
    }

    public double getUitkomst() {
        return uitkomst;
    }

    public void uitkomst(){
        uitkomst = 0;
        for(Artikel artikel : artikels){
            uitkomst += artikel.getPrijs();
        }
    }

    public void handleDelete(Artikel artikel) {
                artikels.remove(artikel);
                notifyObserver(EventType.KLANTVIEW, artikels);
    }

    public void handleHold() {
        List<Artikel> tmpList = new ArrayList<>(artikels);
        double tmpUitkomst;
        artikels.clear();
        artikels.addAll(artikelsHold);
        artikelsHold.clear();
        artikelsHold.addAll(tmpList);
        tmpUitkomst = uitkomst;
        uitkomst = uitkomstHold;
        uitkomstHold = tmpUitkomst;
        notifyObserver(EventType.KLANTVIEW, artikels);
    }

    public void handleAfhandel() {
        notifyObserver(EventType.KLANTVIEW, artikels);
    }
}
